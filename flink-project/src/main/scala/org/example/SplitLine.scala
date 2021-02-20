package org.example

import org.apache.flink.api.common.operators.Order
import org.apache.flink.api.scala._
import org.apache.flink.util.Collector

import scala.reflect.io.Path

object SplitLine {

  import org.apache.flink.streaming.api.functions.sink.SinkFunction

  class WordFileSink extends SinkFunction[String] {
    def main(args: Array[String]): Unit = {

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
              Path(tup._1).createFile().appendAll(s"${tup._2.toString}\n")
              out.collect(tup)
            }
        }
      output.count()
    }
  }
}
