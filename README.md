- [Table of content](#table-of-content)
  * [Introduction](#introduction)
    + [Getting Started](#getting-started)
      - [Prerequisites](#prerequisites)
      - [Installing](#installing)
  * [Running the tests](#running-the-tests)
      - [Unit Testing](#unit-testing)
      - [Regression Testing](#regression-testing)
  * [Deployment](#deployment)
    - [Built With](#built-with)
    - [Authors](#authors)


## Introduction

Parking Lot Service is an automated ticketing system that allows customers to use parking lot without human intervention.

### Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

#### Prerequisites

For running this project, you need to have Java 8 and Maven 3.3.9 at least.

For checking which versions you have, you can run the following commands:

```
java --version

mvn --version
```

For working with maven, you can read the basics from apache's official precise documentation - [*Maven in 5 minutes*](
https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)

#### Installing

A step by step series of examples that tell you how to get a the application up and running

You can run the following 
commands to run the application. From `parking_lot` run below command. 

I. This command will run unit tests and if all the tests are passed then it will start the application

```
parking_lot $ ./bin/setup
```
II. If you want to pass the input file then you can use below command by passing the `file` as command argument.
You can also pass your file as an argument if you want.

```
parking_lot $ ./bin/setup functional_spec/fixtures/file_input.txt
```

### Running the tests

#### Unit Testing

The other corresponding stats are as follows:

**Unit Test Coverage**: 93%

**Line Coverage** : 85%

**Method Coverage** : 86%

For running all these tests you can follow below step:

I. Run the maven test phase from `parking_lot` folder.
```
mvn test
```

#### Regression Testing
For running the functional tests including both e2e and specs, you can run below commands in given order from `parking_lot`.

I. Bring up the application.

```
parking_lot $ ./bin/setup
```


II. Run functional tests.

```
parking_lot $ ruby bin/run_functional_tests
```


### Built With

* [Maven](https://maven.apache.org/) - Dependency Management
 
### Authors

* **Pushpendra Pal** - *Parking lot Service* - [parking-lot]
