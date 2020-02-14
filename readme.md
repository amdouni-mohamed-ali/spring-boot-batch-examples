# Spring Boot Batch Examples

This repository contains simple examples to explain how to use the spring batch features.

This is the list of examples :

```
spring-boot-batch-examples
│
└─── chunk-oriented-processing
└─── tasklet-listener-example
└─── process-database-data
└─── process-csv-file
└─── multiple-datasources-example
└─── abstract-job-step-example
└─── scheduler-example
└─── conditional-flow-example
```

If you are to learn how to use the framework, please follow the order displayed above.

### Prerequisites

To create your development environment, you will need to :

- install java 8 (or above)
- install maven
- install git to clone the project
- you favorite IDE (i'm using intellij) 
- install docker on your machine (we have a single example that uses docker)

### Installing

To clone the project, run this command :

```
    $ git clone https://github.com/amdouni-mohamed-ali/spring-boot-batch-examples.git
    $ cd spring-boot-batch-examples
```

And run this to package the project :

```
    $ mvn clean package
```

```log
[INFO] 
[INFO] spring-boot-batch-examples ......................... SUCCESS [  3.344 s]
[INFO] chunk-oriented-processing .......................... SUCCESS [  3.923 s]
[INFO] process-database-data .............................. SUCCESS [  1.211 s]
[INFO] tasklet-listener-example ........................... SUCCESS [  0.283 s]
[INFO] process-csv-file ................................... SUCCESS [  3.908 s]
[INFO] abstract-job-step-example .......................... SUCCESS [  0.239 s]
[INFO] multiple-datasources-example ....................... SUCCESS [  1.024 s]
[INFO] scheduler-example .................................. SUCCESS [  0.190 s]
[INFO] conditional-flow-example ........................... SUCCESS [  0.248 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  14.950 s
[INFO] Finished at: 2020-02-14T18:01:07+01:00
[INFO] ------------------------------------------------------------------------
```

## Built With

* [Java](https://openjdk.java.net/) - openjdk version "1.8.0_232"
* [Spring Boot](https://spring.io/projects/spring-boot) - version 1.5.10.RELEASE
* [Maven](https://maven.apache.org/) - Dependency Management (version 3.6.0)
* [Intellij](https://www.jetbrains.com/) - IDE (version 11.0.5)
* [Docker](https://www.docker.com/) - To create, deploy, and run applications using containers

## Authors

* **Mohamed Ali AMDOUNI**

![1_AuuygyQOYhHm0lBFjDNzuA](https://user-images.githubusercontent.com/16627692/74552160-e4a3ab00-4f54-11ea-8c53-fe68b8ef4877.png)

