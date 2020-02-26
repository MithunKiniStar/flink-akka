# Flink Akks Integration

1. Flink is a data processing framework.  
2. It can handle both batch and streaming workflows.  
3. One unique feature of Flink is that it does pure streaming in it's streaming mode.

## Flink Documentation
https://ci.apache.org/projects/flink/flink-docs-release-0.9/index.html


## Steps involved in a creating a Flink application (from Flink Documentation)

1. Create an ExecutionEnvironment
2. Initialize input data
3. Apply operations - transformations/filtering/joins/etc
4. Specify how to output
5. Execute the application

## Prerequisites

Java 1.7.*
Maven
Git

## Set up project

```shell
cd /flink-akka/core/
mvn clean package

cd /flink-akka/actor-model/
mvn clean install
mvn clean package

cd /flink-akka/flink-hello-world-with-akka/
mvn clean install
mvn clean package
```



## Run the actor-model which is a remote actor
Run actor-model application


### Run nc (netcat utility) in one terminal

```shell
nc -p 9999 -l
```

### Run the flink stream which excepts the customer and monitor if the particular customer is interested in home loan after 3 hits

Run flink-hello-world-with-akka application


##### Enter in the nc terminal one by one

{"name":"Mithun","email":"kinimithun@gmail.com","mobile":"9986"}
{"name":"Mithun","email":"kinimithun@gmail.com","mobile":"9986"}
{"name":"Mithun","email":"kinimithun@gmail.com","mobile":"9986"}
{"name":"Mithun","email":"kinimithun@gmail.com","mobile":"9986"}
{"name":"Mithun","email":"kinimithun@gmail.com","mobile":"9986","customerFormSubmitted":true}



##### Monitor the result in actor-model application
No output after 1st input
No output after 2nd input
After 3rd input output is : "Customer Notified>>>>true"
No output after 4th input
After 5th input output is : Customer Submitted>>>>true
