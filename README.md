SPRING Boot items-on-sale API

About:
Microservice named "items-on-sale", using Spring boot framework and desired dependency management tool, which returns recommended items list, which are on sale, for a valid user on a shopping website. Before you begin, please consider the requirements below as flexible to your comprehension and assumption. Also note that you can use desired source to retrieve the data.

1. Add an endpoint /recommendations/{userId} to "items-on-sale ". To prepare the recommended items on sale, consider the following:
a) Categories of items the user bought before, consider “my Orders” list, and the user’s rating for these items. The ratings will affect the recommendation list, where the items from higher ranked categories will appear first.
b) The user’s favorite items list categories. “Wish list”
c) The items on sale, from all other categories, which has the highest rankings among all other customers. “Hot deals”
d) Note that items on sale list only changes once daily.
2. Secure your service to only allow calls from the website domain “shopping.rbc.com”
3. Consumers/Users should have valid credentials when calling the microservice or else they will get 403 forbidden error

Technologies :

It was made using Spring Boot, Spring Security and CORS, Spring Data JPA, Spring Data REST. Database is in memory H2.

Configuration Files :

src/resources/application.properties - main configuration file. Here it is possible to change admin username/password to h2 db, as well as change the port number.


How to run
There are several ways to run the application. You can run it from the command line with included Maven Wrapper, Maven or Docker.

Once the app starts, go to the web browser and visit

Admin username: admin

Admin password: admin

User username: user

User password: password

Maven Wrapper
Using the Maven Plugin
Go to the root folder of the application and type:

$ chmod +x scripts/mvnw
$ scripts/mvnw spring-boot:run
Using Executable Jar
Or you can build the JAR file with

$ scripts/mvnw clean package
Then you can run the JAR file:

$ java -jar target/shopping-cart-0.0.1-SNAPSHOT.jar
Maven
Open a terminal and run the following commands to ensure that you have valid versions of Java and Maven installed:

$ java -version
java version "1.8.0_102"
Java(TM) SE Runtime Environment (build 1.8.0_102-b14)
Java HotSpot(TM) 64-Bit Server VM (build 25.102-b14, mixed mode)
$ mvn -v
Apache Maven 3.3.9 (bb52d8502b132ec0a5a3f4c09453c07478323dc5; 2015-11-10T16:41:47+00:00)
Maven home: /usr/local/Cellar/maven/3.3.9/libexec
Java version: 1.8.0_102, vendor: Oracle Corporation
Using the Maven Plugin
The Spring Boot Maven plugin includes a run goal that can be used to quickly compile and run your application. Applications run in an exploded form, as they do in your IDE. The following example shows a typical Maven command to run a Spring Boot application:

$ mvn spring-boot:run
Using Executable Jar
To create an executable jar run:

$ mvn clean package
To run that application, use the java -jar command, as follows:

$ java -jar target/items-on-sales-0.0.1-SNAPSHOT.jar
To exit the application, press ctrl-c.

Docker
It is possible to run items-on-sales using Docker:

Build Docker image:

$ mvn clean package
$ docker build -t items-on-sales-cart:dev -f docker/Dockerfile .
Run Docker container:

$ docker run --rm -i -p 8070:8070 \
      --name items-on-sales\
      items-on-sales-cart:dev
Helper script
It is possible to run all of the above with helper script:

$ chmod +x scripts/run_docker.sh
$ scripts/run_docker.sh
Docker
Folder docker contains:

docker/shopping-cart/Dockerfile - Docker build file for executing shopping-cart Docker image. Instructions to build artifacts, copy build artifacts to docker image and then run app on proper port with proper configuration file.
Util Scripts
scripts/run_docker.sh.sh - util script for running items-on-sales Docker container using docker/Dockerfile
Tests
Tests can be run by executing following command from the root of the project:

$ mvn test
Helper Tools
REST Browser
Go to the web browser and visit http://localhost:8080/

You will need to be authenticated to be able to see this page.

H2 Database web interface
Go to the web browser and visit http://localhost:8080/h2-console

In field JDBC URL put

jdbc:h2:mem:shopping_cart_db
In /src/main/resources/application.properties file it is possible to change both web interface url path, as well as the datasource url.

Swagger
http://localhost:8080/swagger-ui.html


# Running application[![](https://github.com/rits007/items-on-sale/blob/main/running%20application.PNG)]

# Resources[![](https://github.com/rits007/items-on-sale/blob/main/resources.PNG)]
# login[![]()]
# success[![]()]
# authenticationFails[![]()]


