# alby-test

# Project Highlight
This Spring Boot project is built on purpose of Alami Syariah assessment test

It contains simple CRUD process of 2 table, Account and Transaction in PostgreSQL database
In the /doTransaction process, apart from save it to PostgreSQL, Spring Boot will also work as producer and send Topics to Kafka
Then MongoDB will work as sink, stream all the Topics from Kafka and save it in MongoDB collection as history


# Requirement
This project needs :
* IDE
* Apache Maven
* PostgreSQL (installed localy)
* MongoDB (installed localy)
* Apache Kafka (installed localy)


# How to Run This Project

## Setup Apache Kafka
* Install Apache Kafka on `C:\kafka`
* Download libs below and put it all in `C:\kafka\libs`
  * mongo-kafka-connect-1.2.0-all.jar
  * mongodb-driver-3.12.7.jar
  * mongodb-driver-core-3.12.7.jar
  * mongo-java-driver-3.12.6.jar
* Add this line `plugin.path=C:\kafka\plugins` in `connect-standalone.properties`
* Create `MongoSinkConnector.properties` like this
  in here I use :
  * `Topic_Alby` as Topics key
  * `alby` as MongoDB database
  * `transaction_history` as MongoDB collection
  ```
  name=mongo-sink
  topics=Topic_Alby
  connector.class=com.mongodb.kafka.connect.MongoSinkConnector
  tasks.max=1
  key.ignore=true
  connection.uri=mongodb://localhost:27017
  database=alby
  collection=transaction_history
  max.num.retries=3
  retries.defer.timeout=5000
  type.name=kafka-connect
  schemas.enable=false
  ```
 * Start Zookeeper server `.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties`
 * Start Kafka server `.\bin\windows\kafka-server-start.bat .\config\server.properties`
 * Create Topics key on Kafka `.\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic Topic_Alby`
 * Start mongo-kafka-connect `.\bin\windows\connect-standalone.bat .\config\connect-standalone.properties .\config\MongoSinkConnector.properties`
 * Check Topics on Kafka `.\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic Topic_Alby --from-beginning`

## Start MongoDB
You can just run the GUI Apps or use command below
* `cd` to `C:\Program Files\MongoDB\Server\4.4\bin>`
* enter command `mongod`

## Run Spring Boot
Just run Spring Boot normally

# Testing
You can use the `Alby-Test.postman_collection.json` collection

# Result
* Kafka Topics
![Kafka Topics](https://user-images.githubusercontent.com/43963384/116109170-c00d9c00-a6de-11eb-81aa-f2404a1c35cc.PNG)

* MongoDB Sink
![MongoDB Sink](https://user-images.githubusercontent.com/43963384/116108523-2b0aa300-a6de-11eb-8d27-400b57a31d48.PNG)

* PostgreSQL
 * Account
   ![AccountTable](https://user-images.githubusercontent.com/43963384/116109861-648fde00-a6df-11eb-8b0b-33b05bf3fcbc.PNG)

 * Transaction
   ![TransactionTable](https://user-images.githubusercontent.com/43963384/116109927-71accd00-a6df-11eb-9f93-e7a437c5f41f.PNG)


