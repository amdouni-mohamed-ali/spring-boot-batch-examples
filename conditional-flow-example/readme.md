# Description

In this example, we will show you how to make a conditional batch process using spring.

## Overview

We will create a workflow of our batch steps in this example. To have a look at our workflow, check the image below :

![flow-chart](https://user-images.githubusercontent.com/16627692/74549034-33e6dd00-4f4f-11ea-819c-28b7268a4bf2.jpg)

This is the job of our batch :

1. Check if the employee table exists. If yes we gonna distribute the salaries of our employees
2. If the table does not exist, we gonna create it and populate it with data (you can find the used scripts to create and populate the table
in the resources of the project)
3. We gonna search for fortunate employees. A fortunate employee is an employee who is his birthday is this month. If we find fortunate
employees, we will divide the bonus of this month between them.
4. if there is no fortunate employees, we gonna divide the bonus between all the employees of the company
5. finally, we will display the list of our employees and theirs salaries

In this example we will use the H2 database. 
This list of our tasklets are declared in the the `tasklets` package. 

In Spring Batch, a Tasklet is an interface that performs a single task within a Step. The list of our steps beans are declared in the `steps` package.

We've declared common listeners for jobs and steps in the `listeners` package. `JobListener` will be listening on the unique job of ou app.
`StepListener` will be called before and after each step of our job.

And finally the workflow of our batch is in the `jobs` package.

check the documentation for deep understanding :

- https://docs.spring.io/spring-batch/docs/current/reference/html/step.html#conditionalFlow

## Run the application

As this is a spring boot app, you can run the main method from `BatchApplication.java` or use the maven plugin :

```bash
$ mvn spring-boot:run
```

After running the example, you should have this result :

```log
2020-02-14 17:43:27.013  INFO 22421 --- [           main] c.s.t.e.batch.listeners.JobListener      : ### Before JOB - ID#1 NAME#SALARY_DEPOSIT_JOB : STARTED
2020-02-14 17:43:27.030  INFO 22421 --- [           main] c.s.t.e.batch.listeners.StepListener     : ### Before Step - JOB-ID#1 STEP-ID#1 STEP-NAME#CHECK_EMPLOYEE_TABLE_EXISTENCE : STARTED
2020-02-14 17:43:27.049 ERROR 22421 --- [           main] o.s.batch.core.step.AbstractStep         : Encountered an error executing step CHECK_EMPLOYEE_TABLE_EXISTENCE in job SALARY_DEPOSIT_JOB

java.lang.IllegalArgumentException: Employee table does not exist
	at org.springframework.util.Assert.doesNotContain(Assert.java:205) ~[spring-core-4.3.14.RELEASE.jar:4.3.14.RELEASE]

2020-02-14 17:43:27.050  INFO 22421 --- [           main] c.s.t.e.batch.listeners.StepListener     : ### After Step - JOB-ID#1 STEP-ID#1 STEP-NAME#CHECK_EMPLOYEE_TABLE_EXISTENCE : FAILED
2020-02-14 17:43:27.069  INFO 22421 --- [           main] c.s.t.e.batch.listeners.StepListener     : ### Before Step - JOB-ID#1 STEP-ID#2 STEP-NAME#CREATE_EMPLOYEES_TABLE : STARTED
2020-02-14 17:43:27.072 DEBUG 22421 --- [           main] c.s.t.e.b.tasklets.CreateEmployeeTable   : Sql script to create employees : create table employee
(
   id integer not null,
   firstName varchar(255),
   lastName varchar(255),
   sex char,
   salary double,
   birthDate varchar(10),
   entryDate varchar(10),
   releaseDate varchar(10),
   primary key(id)
);
2020-02-14 17:43:27.080  INFO 22421 --- [           main] c.s.t.e.batch.listeners.StepListener     : ### After Step - JOB-ID#1 STEP-ID#2 STEP-NAME#CREATE_EMPLOYEES_TABLE : COMPLETED
2020-02-14 17:43:27.097  INFO 22421 --- [           main] c.s.t.e.batch.listeners.StepListener     : ### Before Step - JOB-ID#1 STEP-ID#3 STEP-NAME#POPULATE_EMPLOYEES_TABLE : STARTED
2020-02-14 17:43:27.135 DEBUG 22421 --- [           main] c.s.t.e.b.t.PopulateEmployeeTable        : Sql script to populate employee table : 

INSERT INTO employee (id, firstName, lastName, sex, salary, birthDate, entryDate)
        VALUES (1, 'john', 'MICHEL', 'M', 0.0, '1889-02-18', '2011-05-07');
INSERT INTO employee (id, firstName, lastName, sex, salary, birthDate, entryDate, releaseDate)
        VALUES (2, 'jean', 'CLAUDE', 'M', 0.0, '1882-12-18', '2020-05-07', '2020-06-30');
INSERT INTO employee (id, firstName, lastName, sex, salary, birthDate, entryDate)
        VALUES (3, 'max', 'DUB', 'M', 0.0, '1993-08-18', '2011-02-07');
INSERT INTO employee (id, firstName, lastName, sex, salary, birthDate, entryDate, releaseDate)
        VALUES (4, 'janet', 'JACKSON', 'M', 0.0, '1995-02-18', '2011-02-12', '2020-02-28');
INSERT INTO employee (id, firstName, lastName, sex, salary, birthDate, entryDate)
        VALUES (5, 'leonel', 'TRAVIS', 'M', 0.0, '1995-11-18', '2011-05-22');

2020-02-14 17:43:27.139  INFO 22421 --- [           main] c.s.t.e.batch.listeners.StepListener     : ### After Step - JOB-ID#1 STEP-ID#3 STEP-NAME#POPULATE_EMPLOYEES_TABLE : COMPLETED
2020-02-14 17:43:27.154  INFO 22421 --- [           main] c.s.t.e.batch.listeners.StepListener     : ### Before Step - JOB-ID#1 STEP-ID#4 STEP-NAME#UPDATE_SALARY : STARTED
2020-02-14 17:43:27.158  INFO 22421 --- [           main] c.s.t.e.batch.tasklets.UpdateSalary      : 5 salaries has been updated
2020-02-14 17:43:27.163  INFO 22421 --- [           main] c.s.t.e.batch.listeners.StepListener     : ### After Step - JOB-ID#1 STEP-ID#4 STEP-NAME#UPDATE_SALARY : COMPLETED
2020-02-14 17:43:27.174  INFO 22421 --- [           main] c.s.t.e.batch.listeners.StepListener     : ### Before Step - JOB-ID#1 STEP-ID#5 STEP-NAME#LOOK_FOR_FORTUNATE : STARTED
2020-02-14 17:43:27.195  INFO 22421 --- [           main] c.s.t.e.batch.listeners.StepListener     : ### After Step - JOB-ID#1 STEP-ID#5 STEP-NAME#LOOK_FOR_FORTUNATE : COMPLETED
2020-02-14 17:43:27.206  INFO 22421 --- [           main] c.s.t.e.batch.listeners.StepListener     : ### Before Step - JOB-ID#1 STEP-ID#6 STEP-NAME#GIVE_FORTUNATE_BONUS : STARTED
2020-02-14 17:43:27.221 DEBUG 22421 --- [           main] c.s.t.e.b.t.GiveBonusFortunateEmployees  : Fortunate employee : Employee{id=1, firstName='john', lastName='MICHEL', sex=M, salary=90.0, birthDate='1889-02-18', entryDate='2011-05-07', releaseDate='null'}
2020-02-14 17:43:27.222 DEBUG 22421 --- [           main] c.s.t.e.b.t.GiveBonusFortunateEmployees  : Fortunate employee : Employee{id=4, firstName='janet', lastName='JACKSON', sex=M, salary=62.0, birthDate='1995-02-18', entryDate='2011-02-12', releaseDate='2020-02-28'}
2020-02-14 17:43:27.229  INFO 22421 --- [           main] c.s.t.e.batch.listeners.StepListener     : ### After Step - JOB-ID#1 STEP-ID#6 STEP-NAME#GIVE_FORTUNATE_BONUS : COMPLETED
2020-02-14 17:43:27.245  INFO 22421 --- [           main] c.s.t.e.batch.listeners.StepListener     : ### Before Step - JOB-ID#1 STEP-ID#7 STEP-NAME#DISPLAY_EMPLOYEES : STARTED
Employee{id=1, firstName='john', lastName='MICHEL', sex=M, salary=190.0, birthDate='1889-02-18', entryDate='2011-05-07', releaseDate='null'}
Employee{id=2, firstName='jean', lastName='CLAUDE', sex=M, salary=10.0, birthDate='1882-12-18', entryDate='2020-05-07', releaseDate='2020-06-30'}
Employee{id=3, firstName='max', lastName='DUB', sex=M, salary=23.0, birthDate='1993-08-18', entryDate='2011-02-07', releaseDate='null'}
Employee{id=4, firstName='janet', lastName='JACKSON', sex=M, salary=162.0, birthDate='1995-02-18', entryDate='2011-02-12', releaseDate='2020-02-28'}
Employee{id=5, firstName='leonel', lastName='TRAVIS', sex=M, salary=77.0, birthDate='1995-11-18', entryDate='2011-05-22', releaseDate='null'}
2020-02-14 17:43:27.254  INFO 22421 --- [           main] c.s.t.e.batch.listeners.StepListener     : ### After Step - JOB-ID#1 STEP-ID#7 STEP-NAME#DISPLAY_EMPLOYEES : COMPLETED
2020-02-14 17:43:27.264  INFO 22421 --- [           main] c.s.t.e.batch.listeners.JobListener      : ### After JOB - ID#1  NAME#SALARY_DEPOSIT_JOB : COMPLETED
```

As you can see the listeners are invoked before and after each step execution. The employee table was not found that's why  we have the the sql scripts displayed on the console.
We have 2 fortunate employees this month john and janet that's why they had a good salary displayed in last step.
