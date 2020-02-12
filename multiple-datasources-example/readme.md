# Description

In this example, we will explain how to configure two datasources in spring batch. 

The first will be used as a spring batch database to store the
executions, parameters ...

The second datasource is our business datasource. We will use it to get data, process it and finally show it. 

## Overview

In this example we will get the list of users from our database. We gonna use postgres this time. Each retrieved user will transformed
to uppercase and then will be displayed on the console.

We are using this configuration (check the application.yml file) :

```yaml
example:
  jpa:
    datasource:
      driver-class-name: org.postgresql.Driver
      url: jdbc:postgresql://${POSTGRES_HOST}:5432/db
      username: admin
      password: password
      hibernate:
        ddl-auto: update
        show_sql: true
      properties:
        hibernate:
          dialect: org.hibernate.dialect.PostgreSQLDialect
          default_schema: batch_schema
```

* POSTGRES_HOST : is the database host. It's gonna be retrieved from the environment variables.
* batch_schema : is the schema that will be created by hibernate to store our data.
* update : is our hibernate strategy. This last will use the `schema-postgres.sql` file in the resources to create the schema.

These parameters are used in the `EntityManagerConfig.java` file to create our business datasource, entity manager and transaction manager.

To configure the spring datasource we have used this :

```yaml
spring:
  batch:
    job:
      enabled: false
    datasource:
      url: jdbc:h2:file:~/test;DB_CLOSE_ON_EXIT=FALSE;DB_CLOSE_DELAY=-1
      driverClassName: org.h2.Driver
      username: sa
      password:
    table-prefix: MDS

  h2:
    console:
      enabled: true
      path: /h2-console
      settings:
        trace: false
        web-allow-others: true
```

As you can see spring will use a h2 as database. The database will stored as a file in ~/test.
* MDS : is the prefix to be used by spring when creating its batch tables. It's a good feature in the case your business and spring
databases are the same.

We've configured the h2 console to have a look at the spring batch generated tables.

These parameters are used in the `BatchDataSourceConfig.java` file to configure the spring batch datasource.

The Job configuration is in the `BatchJobsConfiguration.java` file. It's a very simple job that declares a reader to retrieve
users from the db, a processor to transform the user name to uppercase and a writer to display the list of users on the console. 

In the `BatchController.java` file, we've configured a rest endpoint that expose a method to run our Job.

To know more about the h2 configuration check this post :

- https://www.baeldung.com/spring-boot-h2-database

## Run the application

We will use a docker environment to run our application and a postgres database. Check the `docker-compose.yml` file. We are using 
three containers :
* spring-app : this is our app
* postgres : this is the postgres db
* adminer : this container is used to manage the postgres db

To run this environment use these command (from the project directory) :

```shell script
$ mvn clean package
$ docker-compose up --build
```

If you had a db connection error, try to re-run the app :

```shell script
$ docker-compose restart md-app
```

### Check the spring db

To check if the h2 database has been created, do to the spring app container end check if the test file exists :

```shell script
$ docker exec -it spring-app sh
```

We are inside the container now, run this :

```shell script
/ # ls -ltr ~/test*
-rw-r--r--    1 root     root         12288 Feb 12 13:41 /root/test.mv.db
```

Well the db has been created. You can even check the content, you'll find the spring tables. You can exit now.

To get access to the h2 console, use this url :

- http://localhost:8080/h2-console

![h2-login](https://user-images.githubusercontent.com/16627692/74342986-a23e6a80-4daa-11ea-8cfe-e962d11a536a.png)

You can check the job execution table. For the moment we have nothing because we didn't run any job :

![h2-job-execution-before](https://user-images.githubusercontent.com/16627692/74342984-a23e6a80-4daa-11ea-89de-6d1cb8be299b.png)

### Check our business db

To get access to the postgres GUI, use this link :

- http://localhost:8000

And use this parameters :

![adminer-login](https://user-images.githubusercontent.com/16627692/74342981-a1a5d400-4daa-11ea-8e65-0d09ef2bbe10.png)

The password is password.

To make sure that hibernate used our configuration check :

- that the used schema is batch_schema

![adminer-app-user](https://user-images.githubusercontent.com/16627692/74342975-a074a700-4daa-11ea-9552-39db63ad4d74.png)

- the primary key constraint is app_user_pkey :

![adminer-constraints](https://user-images.githubusercontent.com/16627692/74342978-a10d3d80-4daa-11ea-97a1-29daae480777.png)

Before running the job we gonna populate the db with these data :

```sql
INSERT INTO app_user (id,email,name) VALUES (1,'red@example.com', 'red');
INSERT INTO app_user (id,email,name) VALUES (2,'green@example.com', 'green');
INSERT INTO app_user (id,email,name) VALUES (3,'yellow@example.com', 'yellow');
```

![adminer-add-users](https://user-images.githubusercontent.com/16627692/74342973-9fdc1080-4daa-11ea-8061-86b993af8cac.png)

### Run the job

You can this command to run the job :

```shell script
$ curl -i localhost:8080/batch?action=start
```

```log
HTTP/1.1 200 
Content-Type: text/plain;charset=UTF-8
Content-Length: 9
Date: Wed, 12 Feb 2020 13:52:31 GMT

COMPLETED
```

If you check the app logs, you'll get :

```shell script
$ docker logs spring-app
```

```log
2020-02-12 14:14:38.981  INFO 1 --- [           main] o.s.t.e.batch.readers.DataSourceReader   : The list of users is : [User [id=1, name=red, email=red@example.com], User [id=2, name=green, email=green@example.com], User [id=3, name=yellow, email=yellow@example.com]]
2020-02-12 14:14:39.791  INFO 1 --- [           main] o.s.t.examples.batch.BatchApplication    : Started BatchApplication in 4.518 seconds (JVM running for 4.868)
2020-02-12 14:14:50.550  INFO 1 --- [nio-8080-exec-1] o.s.t.e.b.controller.BatchController     : Received action is start
2020-02-12 14:14:50.614  INFO 1 --- [nio-8080-exec-1] o.s.t.e.batch.readers.DataSourceReader   : Reading : User [id=1, name=red, email=red@example.com]
2020-02-12 14:14:51.616  INFO 1 --- [nio-8080-exec-1] o.s.t.e.batch.writers.DataSourceWriter   : RED@EXAMPLE.COM
2020-02-12 14:14:51.624  INFO 1 --- [nio-8080-exec-1] o.s.t.e.batch.readers.DataSourceReader   : Reading : User [id=2, name=green, email=green@example.com]
2020-02-12 14:14:52.624  INFO 1 --- [nio-8080-exec-1] o.s.t.e.batch.writers.DataSourceWriter   : GREEN@EXAMPLE.COM
2020-02-12 14:14:52.632  INFO 1 --- [nio-8080-exec-1] o.s.t.e.batch.readers.DataSourceReader   : Reading : User [id=3, name=yellow, email=yellow@example.com]
2020-02-12 14:14:53.632  INFO 1 --- [nio-8080-exec-1] o.s.t.e.batch.writers.DataSourceWriter   : YELLOW@EXAMPLE.COM
```

And finally, we gonna check the spring db to see if there is a job execution :

![h2-job-execution-after](https://user-images.githubusercontent.com/16627692/74342983-a1a5d400-4daa-11ea-9e64-7c15e23c2720.png)


