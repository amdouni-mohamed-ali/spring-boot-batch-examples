# Description

In this example, we will process all lines from a csv file using spring batch and store the result in an another file. We gonna provide some Junit tests also.

## Overview

We will use tasklets in this example to gonna read all files from an input csv file using the open csv library, then we will process all of these data. Finally, we gonna write
all the processed data into a generated csv file. Every step from the above description gonna be a batch spring step.

What we will build exactly ?

If you check the application-local.yml for example (local is the profile to active by default), you'll find this conf :

```yaml
batch:
  in-file: /tmp/input_example
  output-file: /tmp/output_example
logging:
  level:
    org.springframework: ERROR
    org.spring.tutorial.examples.batch: TRACE
``` 

The in-file is the full path of the csv input file. This file content can be for example :

```log
company_id;company_name;company_type;emp_id;emp_first_name;emp_last_name;emp_sex;emp_salary
14578;company_1;type_1;2356;David;Walace;M;250.000
14580;company_1;type_1;1358;Michael;Scott;M;63.000
```

This file contains the employees of a company and its salaries. The purpose of this batch is to parse this file and extract the employees in a new file and search for some special
employees (the id of a special employee must be present in the `config/special.properties` file in the resources) to add a bonus to them.

The output-file is the full path of the generated file. For the last example the output will be :

```log
2356,David,WALACE,M,250.0
1358,Michael,SCOTT,M,88.0
```

Guess what ? Michael is a special employee.

All the business is in the *step* package :

* FileReaderStep : In this step we will search for the input file, parse it and transform it to ` Map<String, String[]>`. The key of the map will be the employee id and the value
will be the content of a line from the csv file. Finally, we will put this map in the step execution context so the next step can use it.

* FileProcessDataStep : This step will search for data in the execution context. Then it will search for special employees and transform the map to a list of Employees. Finally,
it will store this list in the execution context.

* FileWriteDataStep : This step will get the list of employees from the context and store them in the output file.

### Run the application

Before running the app, you have to provide the input file path in the application-local.yml file. If you are on windows, check the example of paths provided in the application-dev.yml.

For the content of the file, You can use the file named `examples/input_example` in the `resources/examples` directory. 

As this is a spring boot app, you can run the main method from `BatchApplication.java` or use the maven plugin :

```shell script
$ mvn spring-boot:run
```

After running the project, you should have this result (i'm using the input_example file) :

```log
2020-02-11 11:37:04.575 DEBUG 18027 --- [           main] o.s.t.examples.batch.service.IOService   : create the java path from the input file : /tmp/input_example
2020-02-11 11:37:04.576 DEBUG 18027 --- [           main] o.s.t.examples.batch.service.IOService   : create the java path from the output file : /tmp/output_example
2020-02-11 11:37:04.815  INFO 18027 --- [           main] o.s.t.e.batch.step.FileReaderStep        : start the FileReader step ...
2020-02-11 11:37:04.819 DEBUG 18027 --- [           main] o.s.t.e.batch.step.FileReaderStep        : Start reading the input file from /tmp/input_example
2020-02-11 11:37:04.819 TRACE 18027 --- [           main] o.s.t.e.batch.step.FileReaderStep        : received data
2020-02-11 11:37:04.827 TRACE 18027 --- [           main] o.s.t.e.batch.step.FileReaderStep        : 14578 | company_1 | type_1 | 2356 | David | Walace | M | 250.000 | 
2020-02-11 11:37:04.827 TRACE 18027 --- [           main] o.s.t.e.batch.step.FileReaderStep        : 14579 | company_2 | type_1 | 2357 | Jan | Levinson | F | 110.000 | 
2020-02-11 11:37:04.827 TRACE 18027 --- [           main] o.s.t.e.batch.step.FileReaderStep        : 14580 | company_1 | type_1 | 1358 | Michael | Scott | M | 63.000 | 
2020-02-11 11:37:04.827 TRACE 18027 --- [           main] o.s.t.e.batch.step.FileReaderStep        : 14581 | company_1 | type_1 | 1359 | Kelly | Kapoor | F | 71.000 | 
2020-02-11 11:37:04.827 DEBUG 18027 --- [           main] o.s.t.e.batch.step.FileReaderStep        : finish reading the input file
2020-02-11 11:37:04.829 DEBUG 18027 --- [           main] o.s.t.e.batch.step.FileReaderStep        : entry file was successfully saved to the batch context
2020-02-11 11:37:04.830  INFO 18027 --- [           main] o.s.t.e.batch.step.FileReaderStep        : FileReader step ended successfully
2020-02-11 11:37:04.842  INFO 18027 --- [           main] o.s.t.e.batch.step.FileReaderStep        : start the process data step ...
2020-02-11 11:37:04.842 DEBUG 18027 --- [           main] o.s.t.e.batch.step.FileReaderStep        : get the data from the context
2020-02-11 11:37:04.842 DEBUG 18027 --- [           main] o.s.t.e.batch.step.FileReaderStep        : the last operation was ended successfully
2020-02-11 11:37:04.842 DEBUG 18027 --- [           main] o.s.t.e.batch.step.FileReaderStep        : start process the data
2020-02-11 11:37:04.844 TRACE 18027 --- [           main] o.s.t.e.batch.step.FileReaderStep        : Employee{empId=2356, firstName='David', lastName='WALACE', sex=M, salary=250.0}
2020-02-11 11:37:04.844 TRACE 18027 --- [           main] o.s.t.e.batch.step.FileReaderStep        : Employee{empId=2357, firstName='Jan', lastName='LEVINSON', sex=F, salary=110.0}
2020-02-11 11:37:04.844 TRACE 18027 --- [           main] o.s.t.e.batch.step.FileReaderStep        : Employee{empId=1358, firstName='Michael', lastName='SCOTT', sex=M, salary=88.0}
2020-02-11 11:37:04.844 TRACE 18027 --- [           main] o.s.t.e.batch.step.FileReaderStep        : Employee{empId=1359, firstName='Kelly', lastName='KAPOOR', sex=F, salary=71.0}
2020-02-11 11:37:04.844 DEBUG 18027 --- [           main] o.s.t.e.batch.step.FileReaderStep        : end process data
2020-02-11 11:37:04.846 DEBUG 18027 --- [           main] o.s.t.e.batch.step.FileReaderStep        : employees was successfully saved to the job context
2020-02-11 11:37:04.846  INFO 18027 --- [           main] o.s.t.e.batch.step.FileReaderStep        : Process data step ended successfully
2020-02-11 11:37:04.859  INFO 18027 --- [           main] o.s.t.e.batch.step.FileWriteDataStep     : start the write data to a file step ...
2020-02-11 11:37:04.859 DEBUG 18027 --- [           main] o.s.t.e.batch.step.FileWriteDataStep     : get the data from the context
2020-02-11 11:37:04.859 DEBUG 18027 --- [           main] o.s.t.e.batch.step.FileWriteDataStep     : the last operation was ended successfully
2020-02-11 11:37:04.860 TRACE 18027 --- [           main] o.s.t.e.batch.step.FileWriteDataStep     : generated file
2020-02-11 11:37:04.860 TRACE 18027 --- [           main] o.s.t.e.batch.step.FileWriteDataStep     : Employee{empId=2356, firstName='David', lastName='WALACE', sex=M, salary=250.0}
2020-02-11 11:37:04.860 TRACE 18027 --- [           main] o.s.t.e.batch.step.FileWriteDataStep     : Employee{empId=2357, firstName='Jan', lastName='LEVINSON', sex=F, salary=110.0}
2020-02-11 11:37:04.860 TRACE 18027 --- [           main] o.s.t.e.batch.step.FileWriteDataStep     : Employee{empId=1358, firstName='Michael', lastName='SCOTT', sex=M, salary=88.0}
2020-02-11 11:37:04.860 TRACE 18027 --- [           main] o.s.t.e.batch.step.FileWriteDataStep     : Employee{empId=1359, firstName='Kelly', lastName='KAPOOR', sex=F, salary=71.0}
2020-02-11 11:37:04.860 DEBUG 18027 --- [           main] o.s.t.e.batch.step.FileWriteDataStep     : start writing data
2020-02-11 11:37:04.904 DEBUG 18027 --- [           main] o.s.t.e.batch.step.FileWriteDataStep     : end writing data
2020-02-11 11:37:04.907  INFO 18027 --- [           main] o.s.t.e.batch.step.FileWriteDataStep     : Write the generated file successfully ended
```

The output content is :


```shell script
$ cat /tmp/output_example
```

```log
2356,David,WALACE,M,250.0
2357,Jan,LEVINSON,F,110.0
1358,Michael,SCOTT,M,88.0
1359,Kelly,KAPOOR,F,71.0
```

## Running the tests

You can simply run the unit tests using this command (in the current directory):

```shell script
$ mvn test
```

```log
Results :

Tests run: 4, Failures: 0, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  5.536 s
[INFO] Finished at: 2020-02-11T11:45:04+01:00
[INFO] ------------------------------------------------------------------------
```