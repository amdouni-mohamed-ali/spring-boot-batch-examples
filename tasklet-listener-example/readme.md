# Description

Chunk-oriented processing is not the only way to process in a Step. What if a Step must consist of a simple stored procedure call? 
You could implement the call as an ItemReader and return null after the procedure finishes. However, doing so is a bit unnatural, since there would need to be a no-op ItemWriter. 
Spring Batch provides the TaskletStep for this scenario.

Tasklet is a simple interface that has one method, execute, which is called repeatedly by the TaskletStep until it either returns RepeatStatus.FINISHED or throws an exception to 
signal a failure. Each call to a Tasklet is wrapped in a transaction. Tasklet implementors might call a stored procedure, a script, or a simple SQL update statement.

- https://docs.spring.io/spring-batch/docs/current/reference/html/step.html#taskletStep

In this example, we will show you how to use Tasklet steps in spring batch.

## Overview

In this example we will create a Tasklet that will create a list of users and transform it to uppercase.
Check the tasklet code in the  `ToUpperCaseTasklet.java` file.

Our batch structure is like this :

```log
Job:processDataJob
|
|__ Listener: jobPlanningListener
|__ Step: toUpperCaseStep
    |
    |__ Listener: stepListener
    |__ Tasklet: toUpperCaseTasklet 
```

Listeners will just put a message on the console to inform the user that the job/step begins.

The content of our application.yml is as follow :

```yaml
spring:
  batch:
    job:
      enabled: false

generateError: false
```

Spring will not run the job automatically, check the `BatchApplication.java` file to see how you can run it manually.

By default the tasklet does not generate an error but if you would like to trigger an error change the generateError property to true.

## Run the application

As this is a spring boot app, you can run the main method from `BatchApplication.java` or use the maven plugin :

```bash
$ mvn spring-boot:run
```

After packaging the project, you should have this result :

```log
2020-02-11 10:35:23.905  INFO 13037 --- [           main] o.s.b.c.l.support.SimpleJobLauncher      : Job: [FlowJob: [name=processDataJob]] launched with the following parameters: [{time=1581413723845}]
before job
2020-02-11 10:35:23.916  INFO 13037 --- [           main] o.s.batch.core.job.SimpleStepHandler     : Executing step: [toUpperCaseStep]
before step
User [id=1, name=JOHN, email=JOHN@EXAMPLE.COM]
User [id=2, name=JACK, email=JACK@EXAMPLE.COM]
User [id=3, name=JEREMY, email=JEREMY@EXAMPLE.COM]
after step
after job
2020-02-11 10:35:23.939  INFO 13037 --- [           main] o.s.b.c.l.support.SimpleJobLauncher      : Job: [FlowJob: [name=processDataJob]] completed with the following parameters: [{time=1581413723845}] and the following status: [COMPLETED]
```

The exit status is COMPLETED. After changing the generate error to true, you'll have :

```log
2020-02-11 10:37:48.463  INFO 13265 --- [           main] o.s.b.c.l.support.SimpleJobLauncher      : Job: [FlowJob: [name=processDataJob]] launched with the following parameters: [{time=1581413868399}]
before job
2020-02-11 10:37:48.476  INFO 13265 --- [           main] o.s.batch.core.job.SimpleStepHandler     : Executing step: [toUpperCaseStep]
before step
User [id=1, name=JOHN, email=JOHN@EXAMPLE.COM]
User [id=2, name=JACK, email=JACK@EXAMPLE.COM]
User [id=3, name=JEREMY, email=JEREMY@EXAMPLE.COM]
2020-02-11 10:37:48.502 ERROR 13265 --- [           main] o.s.batch.core.step.AbstractStep         : Encountered an error executing step toUpperCaseStep in job processDataJob

java.lang.RuntimeException: The user demands to generate this runtime error
	at org.spring.tutorial.examples.batch.tasklets.ToUpperCaseTasklet.execute(ToUpperCaseTasklet.java:51) ~[classes/:na]
	at org.spring.tutorial.examples.batch.tasklets.ToUpperCaseTasklet$$FastClassBySpringCGLIB$$1de8a410.invoke(<generated>) ~[classes/:na]
	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204) ~[spring-core-4.3.14.RELEASE.jar:4.3.14.RELEASE]
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:738) ~[spring-aop-4.3.14.RELEASE.jar:4.3.14.RELEASE]

after step
after job
2020-02-11 10:37:48.509  INFO 13265 --- [           main] o.s.b.c.l.support.SimpleJobLauncher      : Job: [FlowJob: [name=processDataJob]] completed with the following parameters: [{time=1581413868399}] and the following status: [FAILED]
```

The exit status is FAILED this time.


