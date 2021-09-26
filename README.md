# Pagacoin APP

This app is a small project for a internal tool to help admin users to transfer PagaCoins between internal user wallets and see their transactions.

This app consists of backend and frontend parts. Backend is an API REST build with SpringBoot framework. The Frontend is a little user interface to interact made with Angular.

## Getting Started

For run the application in your local machine, you have to download the whole project and launch  both parts in paralel.

### Prerequisites


```
nodeJS
```

```
Eclipse (or your favorite Java IDE)
```


### Installing

#### FrontEnd Instalation 
```
Download and install nodeJS
```

when this is completed, then

```
npm install -g @angular/cli
```

```
npm install --save bootstrap@3
```

now we have installed Angular in our machine.

#### BackEnd Instalation 

```
Download and install Eclipse Java (Get Eclipse IDE 2019)
```

now we have installed Eclipse


## Deployment

The easiest way to try this application is deploying both parts in local.
If not, we have to build both parts and deploy it in a server. For Angular part generate dist folder and for Backend a WAR file.

Here it is explained how to do in local.
For Angular part:
```
open cmd/terminal in frontend directory of the project (check you can run node)
```

```
npm install
npm start
```

Once finished, we have running Angular part in http://localhost:4200/

For Spring-Boot part:

Open Eclipse...
```
Import project -> backend folder of the project -> as Maven Project 
```
Before next action, check that you have installed on your IDE Java JDK 1.8, otherwise you will get error when you try to download all depenendencies!
```
Right click on project, Run as -> Maven install
```
wait until we have downloaded all dependencies
```
//Just do it if your IDE does not detect new changes
Right click on project, Maven -> Update Project
```
```
Right click on pagaCoin/backend/src/main/java/api/SpringBootWebApplication.java, 
Run as -> Java application
```
Once finished, Eclipse deploy in local (in a Apache Tomcat embbeded in Eclipse).
Our API is listenning in http://localhost:8081/.

## Testing
There is a small battery of UnitTesting.

```
Run your backend application
```

```
Right click the project on Eclipse -> Junit Test
```

Apart, there is a collection calls for Postman to test the api
```
Import collection in Postman this file -> backend/src/test/resources/api-pagaCoin.postman_collection.json
```
! Atention cause the ids of wallets changes for every execution, so you MUST CHANGE ID of Wallets to run the collection!
## Built With

* [NPM](https://www.npmjs.com/) - Dependency Management of nodeJS
* [Maven](https://maven.apache.org/) - Dependency Management
* [Angular](https://angular.io/) - Platform for building mobile and desktop web applications.
* [Spring-Boot](https://spring.io/projects/spring-boot) - Framework to create stand-alone, production-grade Spring based Applications

## Versioning
* NPM: 6.4.1
* ANGULAR: 7
* ANGULAR/CLI: 7.3.9
* ECLIPSE: 2019-09 R (4.13.0)
* JAVA: 1.8
* SPRING-BOOT: 2.0

## Authors

* **Horaci Salvany**  - [horacisalvany](https://github.com/horacisalvany)

