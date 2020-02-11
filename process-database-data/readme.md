# Description

In this example, we will show you how to process data from database in spring batch.

## Overview

We will create a simple project to process data, our source of data is an a data-source declared in the `DataSourceReader.java` file.
We are using a script to create and populate the database with users. Have a look at the `import.sql` in the resources directory.
These data will be processed by the processor defined in `DataSourceProcessor.java` which will make the name and the email of the users
in uppercase and then passed by the writer of the `DataSourceWriter.java` class which will print all the users in the console.

Check the `BatchApplication.java` file to see how we retrieved the job launcher from the spring context and how to run the job with a parameter.
Why using parameter ?

Well, if you use a file database like mysql the spring will store the status of all your jobs. If one of the jobs has successfully terminated you can't
run it again that why we are using parameters. In this cas the job will be equal to job + parameters and you can change the parameter each time you would
like to re-run the job.

check this post :

- https://mkyong.com/spring-batch/spring-batch-a-job-instance-already-exists-and-is-complete-for-parameters/

## Run the application

As this is a spring boot app, you can run the main method from `BatchApplication.java` or use the maven plugin :

```bash
$ mvn spring-boot:run
```

After packaging the project, you should have this result :

```log
2020-02-02 19:05:56.020  INFO 53708 --- [           main] o.s.batch.core.job.SimpleStepHandler     : Executing step: [toUpperCaseStep]
User [id=1, name=MKYONG@GMAIL.COM, email=MKYONG]
User [id=2, name=ALEX@YAHOO.COM, email=ALEX]
User [id=3, name=JOEL@GMAIL.COM, email=JOEL]
User [id=4, name=MKYONG4@GMAIL.COM, email=MKYONG4]
User [id=5, name=ALEX5@YAHOO.COM, email=ALEX5]
User [id=6, name=JOEL6@GMAIL.COM, email=JOEL6]
User [id=7, name=MKYONG7@GMAIL.COM, email=MKYONG7]
User [id=8, name=ALEX8@YAHOO.COM, email=ALEX8]
User [id=9, name=JOEL9@GMAIL.COM, email=JOEL9]
User [id=10, name=MKYONG10@GMAIL.COM, email=MKYONG10]
User [id=11, name=ALEX11@YAHOO.COM, email=ALEX11]
User [id=12, name=JOEL12@GMAIL.COM, email=JOEL12]
2020-02-02 19:05:56.080  INFO 53708 --- [           main] o.s.b.c.l.support.SimpleJobLauncher      : Job: [FlowJob: [name=processDataJob]] completed with the following parameters: [{}] and the following status: [COMPLETED]
2020-02-02 19:05:56.082  INFO 53708 --- [           main] o.s.t.examples.batch.BatchApplication    : Started BatchApplication in 4.544 seconds (JVM running for 5.046)
2020-02-02 19:05:56.090  INFO 53708 --- [           main] o.s.b.c.l.support.SimpleJobLauncher      : Job: [FlowJob: [name=processDataJob]] launched with the following parameters: [{time=1580666756083}]
2020-02-02 19:05:56.099  INFO 53708 --- [           main] o.s.batch.core.job.SimpleStepHandler     : Executing step: [toUpperCaseStep]
2020-02-02 19:05:56.109  INFO 53708 --- [           main] o.s.b.c.l.support.SimpleJobLauncher      : Job: [FlowJob: [name=processDataJob]] completed with the following parameters: [{time=1580666756083}] and the following status: [COMPLETED]
Exit Status : COMPLETED
```

Our data has been successfully transformed to uppercase.


