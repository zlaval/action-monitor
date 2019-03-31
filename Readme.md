# Action manager

#### Content

---
- [Description](#description)
- [Architecture](#architecture)
- [API Docs](#api-docs)
- [How to use](#how-to-use)
- [Run integration tests](#run-integration-tests)
- [Docker build](#docker-build-and-run)
- [Connect to H2](#connect-to-the-h2-database-running-inside-docker)
- [Test](#test)
- [Known issues](#known-issues)

#### Description

---
This application contains two runnable modules:
* monitor-backend
* embedded database

Monitor backend module waits for JMS messages and broadcast them to the connected users over websocket.
Embedded database module contains an H2 SQL Db - with a trigger on insert,update,delete - which send 
the event into the MQ. 

#### Architecture

---
![picture alt](https://github.com/zlaval/Diagrams/blob/master/architecture.png "diagram")

#### API Docs

---
REST endpoints use Swagger to create api docs. The docs are available on the following urls:

|URL|Description|
|---|---------|
|/v2/api-docs|JSON format|
|/swagger-ui.html|Swagger UI|

#### How to use

---
Easiest way to try the application is to build and run the docker container.
Application can run from IDE or with `java -jar` command after maven install, but
in this case, ActiveMQ with TCP port 61616 must be started on the local machine.

MQ requiered, install start etc


#### Run integration tests

---
Only unit test run inside maven test phase. To run unit test, the following command must be executed:
>`verify -Pintegration-test`

#### Docker build and run

---
>*Warning*
>the `start.sh` file must contains `LF` line ending even on Windows or classic Mac!!

All components and the MQ is placed into one single container to preserve simplicity.
Before create container, `mvn package` command must be successfully finished.
Dockerfile contains all necessary dependencies and commands.
The `docker build -t action-monitor .` command create the proper image.
After image has been built, the 
`docker run --name am -p 61616:61616 -p 8161:8161 -p 9570:9570 -p 9580:9580 -p 9090:9090 action-monitor`
command starts the container and expose all necessary ports.

|Port number| Description|
|:---------:|:----------|
|61616      |The TCP port of ActiveMQ, use it to connect the MQ. Not mandatory to expose|
|8161       |The Admin Port of ActiveMq. `http://<docker-host>:8161/admin/queues.jsp`|
|9570       |Port of the Action manager|
|9580       |Port to H2's web console|
|9090       |TCP port to H2|

#### Connect to the H2 database running inside docker

---
>jdbc:h2:tcp://<docker-host>:9090/~/appdb
>
>user / password: sa /sa

#### Test

---
The application is covered with unit tests, and some integration test for representation purpose.

#### Known issues

---
* Queue name is hardcoded.
* Embedded database module's properties (like MQ connection) are hardcoded.
* Embedded database module not reset JMS connection after MQ restart.
* E2E/ChaosMonkey etc tests are missing.




