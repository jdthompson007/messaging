# messaging
Messaging Application with Rabbit MQ
====================================
Contains a producer and a consumer application both written in Java 8 with Spring Boot.

To create eclipse files run gradle (or gradlew.bat or gradlew) eclipse
To build run gradle (or gradlew.bat or gradlew) build

docker login and enter username/password to authenticate docker hub
gradle buildDocker -Ppush to create docker image in docker hub

docker-compose up 						run from root of consumer or producer to run the RabbitMQ via the docker-compose.yml file
docker run jdthompson007/consumer		will run the consumer
docker run jdthompson007/producer		will place message son the queue

NB Known issue.  Will not work with localhost when running in 3 separate containers.  

application.properties file in src/main/resources now has host address of 192.168.99.100, this will need to change in another env
(and will hence require another docker image upload for consumer and producer)