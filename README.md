# messaging
Messaging Application with Rabbit MQ
====================================
Contains a producer and a consumer application both written in Java 8 with Spring Boot.

To create eclipse files run gradle (or gradlew.bat or gradlew) eclipse
To build run gradle (or gradlew.bat or gradlew) build

docker login and enter username/password to authenticate docker hub
gradle buildDocker -Ppush to create docker image in docker hub

| Command                                                                                | Comment
-----------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------
|docker-compose up 																		 | run from root of consumer or producer to run the RabbitMQ via the docker-compose.yml file
|docker run jdthompson007/consumer --spring.rabbitmq.host=<rabbitmq server ip address>   | will run the consumer
|docker run jdthompson007/producer --spring.rabbitmq.host=<rabbitmq server ip address>	 | will place messages on the queue

application.properties file in src/main/resources now has 

spring.rabbitmq.host=localhost

this must be overridden in the docker environment
(and will hence require another docker image upload for consumer and producer)

Useful command for killing off all docker containers (leaving consumers hanging around will mess up the stats)
docker rm -f $(docker ps -a -q) 

docker-machine ip			gets docker ip address
docker-machine ip dev		gets docker ip address of dev