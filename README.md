# Chapter 13: Performance Tuning Your Services

This repository contains the code from the book for Chapter 13, Performance Tuning Your Services. See the [main book page](https://github.com/realworldjava) for the list of all chapter specific repositories.

# How this repository is organized

In this repository, all code is in the main branch. There are three folders as described below. Additionally the JMX file used by JMeter is in the src/main/resources directory.

| Folder Name  | Contents |
| ------------- | ------------- |
| benchmark  | A brute force benchmark |
| jmh-benchmark  | A JMH benchmark |
| rec-center | A poorly performing application |

# How to run the examples in this chapter

If you want to use an IDE, import the repository as a Maven project so you have the proper  dependencies.

If you want to use the command line, cd to the directory for that example and run 
```
mvn verify
```

## benchmark

Two classes to use for testing microbenchmarking. SystemTimingFast and SystemTestingSlow show different behaviors.

## jmh-benchmark

Java MicroBenchmarking framework project for testing the benchmark example. It was generated with

```
mvn archetype:generate \
   -DinteractiveMode=false \
   -DarchetypeGroupId=org.openjdk.jmh \
   -DarchetypeArtifactId=jmh-java-benchmark-archetype \
   -DgroupId=com.wiley.realworldjava.jmh \
   -DartifactId=jmh-benchmark \
   -Dversion=1.0.0

```

## rec-center

Start the Spring application with:
```
java -jar target/rec-center-line-0.0.1-SNAPSHOT.jar
```

RecCenterApplication Spring Boot application. It ontains 
* http://localhost:8080/status - shows which pieces of equipment are available and forms to borrow/return equipment
* http://localhost:8080/emailSummary - reads a file and show statistics. Fast the first time and slow all subsequent times.

# Clickable Links from the Further References Section

* [JMH](https://github.com/openjdk/jmh)
* [java configuration options](https://docs.oracle.com/en/java/javase/21/docs/specs/man/java.html)
* [jstat configuration options](https://docs.oracle.com/en/java/javase/21/docs/specs/man/jstat.html)
* [Detailed presentation on garbage collection](https://speakerdeck.com/cguntur/java-garbage-collection-a-journey-until-java-13-darkbg)
* [Download JMeter](https://jmeter.apache.org/download_jmeter.cgi)
* [JMeter documentation](https://jmeter.apache.org/usermanual/index.html)
* [Download Java Mission Control](https://www.oracle.com/java/technologies/jdk-mission-control.html)
* [Download Java VisualVM](https://visualvm.github.io/download.html)