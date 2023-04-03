## Spring Boot

Spring Boot is a framework / utility that runs on top of the actual Spring Framework.

## Why do we use it?

* Makes our life easy
* in what way?
  - Rapid Application Development (reduced time) to remove the challenges we had faced during the
    Application Development Cycle.

## Challenges / Overheads

  - Configuration Hassles
    - Earlier, we had to configure everything in XML (`spring.xml` file) - Initial days
    - Later, we had moved to Annotations (`@Configuration`, `@Component`, `@ComponetScan` etc.,)
    - For a Spring MVC project, we had to specify all the dependencies manually with the corresponding version numbers
      of each dependency in the `pom.xml` file.
  - Deployment
    - We had to explicitly prepare a `.war` file and deploy it in Tomcat.
    - With the Maven Tomcat plugin, it was reduced to an extent, but still we had to perform manual steps.
      - We need to explicitly add a Maven Tomcat Plugin in the `pom.xml` file.

## Spring Boot Magic

  - No configuraiton. It works by Convention.
  - Embedded Application Server (Embedded Tomcat) - the Tomcat is bundled within the Spring Boot Library
    depends on the starter package we choose.
  - It has got a few *starter projects*- which will have the necessary dependencies that are commonly required, to save the time of the Developer.
    - *Web* - `springboot-starter-web` : It contains all the libraries required for a common Web Application including the Web Container like Tomcat , Jetty etc.,
      - Java EE Web, JSON Support, Tomcat, etc.,
      - Rest is inclusive in Web.
    - *Data* - `springboot-starter-data` : It contains all the libraries required for the Data Management
      - Spring JDBC, MySQL, H2 (In Memory database) etc.,
    - *Data JPA* - `springboot-starter-data-jpa`: It contains all the libraries required for the Data Management using JPA - *Java Persistence API* - using ORM (_Object Relational Mapping_).
      - Spring Data JPA, Hibernate, etc.,
    - *Test* - `springboot-starter-test`: It contains all the libraries required for the Unit and Integration Testing, including the Mocking frameworks.
      - JUnit, Mockito, etc.,
    - *Dev Tools* - It is an interesting and most liked feature introduced in Spring Boot which will automatically scan for the changes you made in the IDE (Eclipse, Spring Tool Suite (STS), Intellij IDEA) and deploy it in the Web Container, there by the manual efforts of the Developer.
      - No need of Stop the Server, Clean Build, Deploy it in the Server steps!
    - *Actuator* - It is again an useful plugin for monitoring the end points and the health status of the Application.

> *Note*: Spring Boot works on CoC - Convention over Configuration methodology.
