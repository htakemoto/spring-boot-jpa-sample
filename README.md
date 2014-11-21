# Spring Boot JPA Sample

Spring Boot with JPA integration with one to many relationship tables (user, item)


## Requirements

* [Java Platform (JDK) 6 or up](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Apache Maven 3.x](http://maven.apache.org/)


## Quick start

1. Import the project into Eclipse as Maven project
2. Find `Application.java` from side bar, right click on the file, select 'Run As' > 'Java Application' from menu.
3. Point your browser to [http://localhost:8080/users](http://localhost:8080/users)
4. There are more endpoints. For details, check `UserController.java`


## Database Setup

The project uses embedded database by default. To use MySQL, follow the step.

1. Comment out `embedded database` part and uncomment `MySQL JDBC connector` part on `pom.xml`
2. Uncomment `spring.datasource` part, and correct the configuration on `application.yml`

This application executes import.sql at launch on embedded database while schema.sql is executed on external database.
