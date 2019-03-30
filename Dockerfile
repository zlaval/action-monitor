FROM rmohr/activemq

VOLUME app
ADD start.sh start.sh
ADD /monitor-backend/target/monitor-backend.jar app/monitor-backend.jar
ADD /embedded-database/target/embedded-database.jar app/embedded-database.jar
CMD ["bash", "start.sh"]



