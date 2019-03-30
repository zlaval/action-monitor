//TODO replace with doc

notes:

run it:
verify -Pintegration-test

embedded-db: set up db, table and trigger
/h2-console
jdbc:h2:~/appdb;AUTO_SERVER=TRUE
sa/sa

queue name hardcoded in the demo


apidoc url:
/v2/api-docs
/swagger-ui.html


db->mq connection not reset if mq restart

all in one container to demo

docker build -t action-monitor .
   docker run --name am -p 61616:61616 -p 8161:8161 -p 9570:9570 -p 9580:9580 -p 9090:9090 action-monitor
 jdbc:h2:tcp://192.168.99.100:9090/~/appdb
 
 
 
  start sh line end must be LF