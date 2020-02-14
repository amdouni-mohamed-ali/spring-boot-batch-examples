# Description

In this example, we will explain how to schedule tasks using spring batch.

## Overview

We will build an application that runs the main job of our app every five seconds by using Spring’s @Scheduled annotation.

Our job uses the chunk oriented processing, the source of our data is an array of strings declared in the `DataSourceReader.java` file.
These array will be processed by the processor defined in `DataSourceProcessor.java` which will transform the data to uppercase and then passed 
it to the writer of the `DataSourceWriter.java` class which will display it on the console.

## Run the application

As this is a spring boot app, you can run the main method from `BatchApplication.java` or use the maven plugin :

```bash
$ mvn spring-boot:run
```

After packaging the project, you should have this result :

```log
2020-02-12 17:24:20.071  INFO 23730 --- [pool-1-thread-1] o.s.batch.core.job.SimpleStepHandler     : Executing step: [toUpperCaseStep]
SPRING
BATCH
SCHEDULER
2020-02-12 17:24:20.094  INFO 23730 --- [pool-1-thread-1] o.s.b.c.l.support.SimpleJobLauncher      : Job: [FlowJob: [name=processDataJob]] completed with the following parameters: [{JobIdentifier=1581524660004}] and the following status: [COMPLETED]
Job finished with status :COMPLETED
Starting the job ...
2020-02-12 17:24:25.003  INFO 23730 --- [pool-1-thread-1] o.s.b.c.l.support.SimpleJobLauncher      : Job: [FlowJob: [name=processDataJob]] launched with the following parameters: [{JobIdentifier=1581524665000}]
2020-02-12 17:24:25.009  INFO 23730 --- [pool-1-thread-1] o.s.batch.core.job.SimpleStepHandler     : Executing step: [toUpperCaseStep]
SPRING
BATCH
SCHEDULER
2020-02-12 17:24:25.018  INFO 23730 --- [pool-1-thread-1] o.s.b.c.l.support.SimpleJobLauncher      : Job: [FlowJob: [name=processDataJob]] completed with the following parameters: [{JobIdentifier=1581524665000}] and the following status: [COMPLETED]
Job finished with status :COMPLETED
Starting the job ...
2020-02-12 17:24:30.006  INFO 23730 --- [pool-1-thread-1] o.s.b.c.l.support.SimpleJobLauncher      : Job: [FlowJob: [name=processDataJob]] launched with the following parameters: [{JobIdentifier=1581524670001}]
2020-02-12 17:24:30.009  INFO 23730 --- [pool-1-thread-1] o.s.batch.core.job.SimpleStepHandler     : Executing step: [toUpperCaseStep]
SPRING
BATCH
SCHEDULER
2020-02-12 17:24:30.017  INFO 23730 --- [pool-1-thread-1] o.s.b.c.l.support.SimpleJobLauncher      : Job: [FlowJob: [name=processDataJob]] completed with the following parameters: [{JobIdentifier=1581524670001}] and the following status: [COMPLETED]
Job finished with status :COMPLETED
```

The job is running each 5 seconds.

We are using the cron syntax to schedule the job :

* @Scheduled(cron = "*/5 * * * * *")

Here is some explanations :

cron is represented by six fields : second, minute, hour, day of month, month, day(s) of week

- \* : every time unit. * in the `minute` field minute means “for every minute”.
- */X : every X time unit.
- X/Y : it is used to specify the incremental values. For example, a “5/15” in the `minute` field, means at “5, 20, 35 and 50 minutes of an hour”
- \- : (range) it is used to determine the value range. For example, “10-11” in `hour` field means “10th and 11th hours”
- , : (values) it is used to specify multiple values. For example, “MON, WED, FRI” in `day-of-week` field means on the days “Monday, Wednesday, and Friday”
- ? : any value , not specific. It is utilized in the `day-of-month` and `day-of-week` fields to denote the arbitrary value – neglect the field value. For example, if we want to fire a script at “5th of every month” irrespective of what the day of the week falls on that date, then we specify a “?” in the `day-of-week` field.

And examples :

| Example  | Frequency  |
|---|---|
|  0 0 * * * * | the top of every hour of every day  |
|  */10 * * * * * |  every ten seconds |
|  0 0 8-10 * * * |  8, 9 and 10 o'clock of every day |
|  0 0 6,19 * * * | 6:00 AM and 7:00 PM every day  |
|  0 0/30 8-10 * * * |  8:00, 8:30, 9:00, 9:30, 10:00 and 10:30 every day |
|  0 0 9-17 * * MON-FRI |  on the hour nine-to-five weekdays |
|  0 0 0 25 12 ? |  every Christmas Day at midnight |

Here's examples that you can use directly in the code :

```java
@Scheduled(cron="5 30 6 * * 1-5")     // every working day (Monday-Friday) at 6 AM 30 minutes 5 seconds

@Scheduled(cron="*/5 28 9 21 4 ?")     // every 21 April at 9 AM 28 minutes.
// every 5 seconds will be started for just one minute (). if we exceed the minute 28 the job will be stopped.

@Scheduled(cron="0 0/1 9 21 4 SAT")  // 21 April at 9 AM every 1 minute -> if the day of the week is a saturday

@Scheduled(cron="0 0 0/1 21 * ?")   // 21 April every 1 hour
```


