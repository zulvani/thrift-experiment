# thrift-experiment
This project is implement Apache Thrift and explore these feature

## How to Run
* Generate thrift service and type for PHP and Java language
```sh
$ thrift --gen java add.thrift
$ thrift --gen php add.thrift
```

* Compile project zulvani-thrift and then run
```sh
$ mvn clean package
$ java -jar target/zulvani-thrift-0.0.1-SNAPSHOT.jar
```
* It should show you message: "Starting the simple server..."
* Run project zulvani-thrift-client as Java Client
* Run project zulvani-thrift-client-php as PHP Client
