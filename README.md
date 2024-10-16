<p align="center">
  <img src="imagenes/logos.png" width="300">
</p>

# LiterAlura Project

Repository for the LiterAlura challenge of the "BackEnd Developer" path from Alura Latam and ORACLE ONE.


## Badges

![GitHub License](https://img.shields.io/github/license/Nicole-lq/Challenge_literatura_NLQ)
![Maven Central Version](https://img.shields.io/maven-central/v/org.apache.maven.plugins/maven-compiler-plugin)
![GitHub Release](https://img.shields.io/github/v/release/Nicole-lq/Challenge_literatura_NLQ?filter=v0.1.0-beta&display_name=tag)


## Table of Contents

* [LiterAlura Project](#literAlura-project)

* [Badges](#badges)

* [Table of Contents](#table-of-contents)

* [Project Description](#project-description)

* [Project Status](#project-status)

* [Main Features](#main-features)

* [Access to the Project](#access-to-the-project)

* [Technologies Used](#technologies-used)
  
* [Gutendex API](#gutendex-api)
  
* [Medal](#medal)

* [Developer](#developer)



## Project Description

This project is implemented as a Spring Boot application designed to manage a book catalog, interacting with a PostgreSQL database. The application includes the functionality of retrieving books based on their language through a custom JPA repository, all based on information obtained from the Gutendex API.

## Main Components

* JPA Entities
* Repositories
* Services
* JPA Configuration
* Main Application

## Workflow

The work methodology followed an agile development system, using the Trello tool, considering the following stages:

* "Ready to start"
* "In Development"
* "Paused"
* "Completed"

The user interface can be seen below:

[<img src ="imagenes/Trello.png">](https://trello.com/b/WDyMPDMb/literalura-challenge-java)


## Project Status

The project has been developed to meet the requirements of the challenge, but there are still improvements to be made. For example, JPA configuration could be optimized, persistence unit management simplified, and unit tests added to ensure the program's stability and functionality.

### Main Features

* Integration with Gutendex API.
* Storage in PostgreSQL.
* Use of Spring Boot.
* JPA Configuration.
* Book Repository.
* Exception Handling.
* Dependency Injection.

## Access to the Project

You can clone this repository using the following command:

```sh
git clone https://github.com/Nicole-lq/Challenge_literatura_NLQ.git
```

## Technologies Used

* Java 17
* Maven
* HttpClient
* JSON
* IntelliJ IDEA

## Gutendex API

The Gutendex API is an interface that provides access to a collection of electronic books from Project Gutenberg, which are in the public domain. This API allows you to search and retrieve detailed information about books based on various criteria such as title, author, language, and subject. It also offers filtering, sorting, and pagination functionalities, making it easy to manage large datasets. The API returns results in JSON format, facilitating integration into different platforms, allowing users to incorporate public domain literary resources into personal projects quickly and easily.

[<img src= "imagenes/Gutendex.png">](https://gutendex.com)

## Medal
<p align="center">
<img src="imagenes/Insignia.png" width="300">
</p>

## Developer

|[<img src="https://avatars.githubusercontent.com/u/84999245?s=96&v=4"><br><sub> Nicole Lastra Quiroz </sub>](https://github.com/Nicole-lq)|
|---|


