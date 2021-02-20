package org.example

import org.apache.flink.api.common.io.FileOutputFormat
import org.apache.flink.api.common.operators.Order
import org.apache.flink.api.scala._
import org.apache.flink.core.fs
import org.apache.flink.core.fs.FileSystem.WriteMode
import org.apache.flink.util.Collector

import scala.reflect.io.Path

object SplitLine {
  def main(args: Array[String]): Unit = {
    val rootPath: fs.Path = args.headOption match {
      case None => new fs.Path("./output")
      case Some(path) => new fs.Path(path)
    }

    // set up the execution environment
    val env = ExecutionEnvironment.getExecutionEnvironment

    // get input data
    val text = env.readCsvFile[(String, Int)](getClass.getResource("/data.csv").getPath)

    // execute and print result
    val output: DataSet[(String, Int)] = text
      .groupBy(0)
      .sortGroup(1, Order.ASCENDING)
      .reduceGroup {
        (in: Iterator[(String, Int)], out: Collector[(String, Int)]) =>
          in foreach{ tup =>
            out.collect(tup)
          }
      }

    output
      .partitionByHash(0)
      .output(new FileOutputFormat[(String, Int)](rootPath) {
        this.setOutputDirectoryMode(FileOutputFormat.OutputDirectoryMode.ALWAYS)
        this.setWriteMode(WriteMode.OVERWRITE)
        override def writeRecord(record: (String, Int)): Unit =
          Path(s"${this.outputFilePath.getPath}/${record._1}")
            .createDirectory()
            .createFile()
            .appendAll(s"${record._2}\n")
      })
    env.execute()
  }
}
