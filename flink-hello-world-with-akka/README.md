# Introduction to Big Data Fall 2015

# Flink Intro

1. Flink is a data processing framework.  
2. It can handle both batch and streaming workflows.  
3. One unique feature of Flink is that it does pure streaming in it's streaming mode.

## Flink Documentation
https://ci.apache.org/projects/flink/flink-docs-release-0.9/index.html

# Flink Examples

These examples are based Flink version 0.9.1.

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
mkdir -p ~/Documents/projects/intro

cd ~/Documents/projects/intro
```

## Check out flink-hello-world from github.com

```shell
git clone https://github.com/mans2singh/flink-hello-world.git flink-hello-world
```

## Build flink project

```shell
cd ~/flink-hello-world/
mvn clean install
```
## Setup eclipse IDE (optional)

```shell
mvn eclipse:eclipse
```

## Prepare class path for the examples

```shell
mvn dependency:copy-dependencies
export CP=$(find ./target/dependency/ | xargs | sed  's/ /:/g'):./target/flink-hello-world-1.0-SNAPSHOT.jar
```

## Run the WordCountSocketStream example that counts words from a stream

### Run nc (netcat utility) in one terminal

```shell
nc -lk 9999
```

### Run the flink stream word count in the terminal where we prepared the classpath for the flink-hello-world project

```shell
java -cp $CP com.mans2singh.intro.big.data.stream.WordCountSocketStream localhost 9999
```
##### Enter in the nc terminal

this is a good day

##### Result

(is,1)
(a,1)
(good,1)
(day,1)
(this,1)

### Run the flink word count with filter code in the terminal where we prepared the flink-hello-world classpath

```shell
java -cp $CP com.mans2singh.intro.big.data.stream.WordCountWithFilterSocketStream localhost 9999
```
##### Enter in the nc terminal

this is a good day

##### Result

(is,1)
(good,1)
(day,1)
(this,1)

## Run the word distinct example that produces distinct words

```shell
java -cp $CP com.mans2singh.intro.big.data.batch.WordDistinctCommandLine "this is the test of the new system"
```

##### Result

(system)
(this)
(new)
(is)
(the)
(of)
(test)

## Run the file join example example which combines word counts from two files

```shell
java -cp $CP com.mans2singh.intro.big.data.join.WordJoinBatch file://$PWD/src/main/resources/joinFile1.txt file://$PWD/src/main/resources/joinFile2.txt
```

##### Result

((john,2),(john,1))
((is,2),(is,2))
((happy,1),(happy,1))
((mary,1),(mary,1))
((very,1),(very,1))

## Run the word count filter example that removes articles and counts rest of the words

```shell
java -cp $CP com.mans2singh.intro.big.data.batch.WordFilterCountCommandLine "this is a test of the new system"
```

##### Result

(new,1)
(of,1)
(system,1)
(this,1)
(test,1)
(is,1)


## Run the command line word count example which counts words in a sentence passed in the command line

```shell
java -cp $CP com.mans2singh.intro.big.data.batch.WordCountCommandLine "this is a test of the new system"
```

##### Result

(is,1)
(test,1)
(the,1)
(new,1)
(of,1)
(a,1)
(system,1)
(this,1)
