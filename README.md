# Flink
A Flink application project using Scala and SBT.

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
