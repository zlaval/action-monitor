# Action manager

#### API Docs

---
REST endpoints use Swagger to create api docs. The docs are available on the following urls:

|URL|Description|
|---|---------|
|/v2/api-docs|JSON format|
|/swagger-ui.html|Swagger UI|



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


#### Known issues

---
* Queue names are hardcoded
* Embedded database module's properties (like MQ connection) are hardcoded
* Embedded database module not reset JMS connection after MQ restart
* E2E/ChaosMonkey etc tests are missing


//TODO replace with doc

notes:

embedded-db: set up db, table and trigger
/h2-console
jdbc:h2:~/appdb;AUTO_SERVER=TRUE
sa/sa

test with some style
it test not complet, some sample
database project tests not written



