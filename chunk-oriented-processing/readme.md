# Description

In this example, we will show you how to configure a simple spring batch project.

- https://docs.spring.io/spring-batch/docs/current/reference/html/step.html#chunkOrientedProcessing

## Overview

We will create a simple project to process data, our source of data is an array of strings declared in the `DataSourceReader.java` file.
These data will be processed by the processor defined in `DataSourceProcessor.java` and then passed by the writer of the `DataSourceWriter.java`
class.

We have one job and one step declared in `BatchConfiguration.java`. If you check the `BatchApplication.java` class, you will notice that
we've excluded the `DataSourceAutoConfiguration.class`. As this class will look for a data-source configuration in you project.
I you don't want to exclude the class, you can define a real data-source (oracle, mysql ...) in you project or you can just add an in-memory
data-source dependency like H2 to the pom.xml.

## Run the application

As this is a spring boot app, you can run the main method from `BatchApplication.java` or use the maven plugin :

```bash
$ mvn spring-boot:run
```

After packaging the project, you should have this result :

```log
2020-02-02 18:21:08.681  INFO 31888 --- [           main] o.s.b.c.l.support.SimpleJobLauncher      : Job: [FlowJob: [name=processDataJob]] launched with the following parameters: [{}]
2020-02-02 18:21:08.706  INFO 31888 --- [           main] o.s.batch.core.job.SimpleStepHandler     : Executing step: [toUpperCaseStep]
THIS
IS
A
SIMPLE
SPRING
BATCH
EXAMPLE
2020-02-02 18:21:08.765  INFO 31888 --- [           main] o.s.b.c.l.support.SimpleJobLauncher      : Job: [FlowJob: [name=processDataJob]] completed with the following parameters: [{}] and the following status:
 [COMPLETED]
2020-02-02 18:21:08.768  INFO 31888 --- [           main] o.s.t.examples.batch.BatchApplication    : Started BatchApplication in 1.489 seconds (JVM running for 12.381)
```

Our data has been successfully transformed to uppercase.


