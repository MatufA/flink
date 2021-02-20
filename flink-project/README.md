# Flink Samples
A Flink application project using Scala and SBT.  
Job Description:  
* Load the attached headerless  CSV (column separated value) file. (resources/data.csv)
* Each line has 2 values, a letter (A-F), and a number.
* For each letter sort the number in ascending order.
* For each letter dump the data into a separate file.

# Prerequisites
Scala version 2.12.11.
Flink version 1.12.1.
Sbt version 1.3.13.

# Usage
You can set a program argument to change the root output path, otherwise ./output.

```
flink run -c org.example.FileProcessor /path/to/Flink Project-assembly-0.1-SNAPSHOT.jar "/path/to/default/output"
```


You can also run your application from within IntelliJ:  
select the classpath of the 'mainRunner' module in the run/debug configurations.
Simply open 'Run -> Edit configurations...' and then select 'mainRunner' from the "Use classpath of module" dropbox. 

**Note- the data samples are under the resources folder**

# Output Example
After the execution will be created the following output folder:  
![image](https://user-images.githubusercontent.com/32271159/108609776-f6762480-73d8-11eb-9c90-8a4270bdf5e1.png)

File content example (A):  
![image](https://user-images.githubusercontent.com/32271159/108609869-992ea300-73d9-11eb-8ee6-3a12b801b606.png)