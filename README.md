## Spring Boot

Spring Boot is a framework / utility that runs on top of the actual Spring Framework.

## Why do we use it?

* Makes our life easy
* in what way?
  - Rapid Application Development (reduced time) to remove the challenges we had faced during the
    Application Development Cycle.
  - Having some *sensible default* values. And, remember most/all of them can be *overridden* either by the _Configuration_ or through _Code_.
    - Embedded Server : `Tomcat`
    - Tomcat Port # : `8080`
    - JPA - Form ORM, it uses `Hibernate` as Implementation of the JPA etc.,
    - `Slf4j` for the logging framework.
    - `json` is the automatic conversion of an Object as a Response - via the `starter-json`

> *Note*: In Spring boot, the configuration for all the defaults or other additional properties, is `application.properties`, available under the `src/main/resources` directory.

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
  - Embedded Application Server (Embedded Tomcat) - the Tomcat is bundled within the Spring Boot Library depends on the starter package we choose.
  - No need to mention the version of any dependency. The *parent* pom takes care of the version of all the dependencies, and now the responsibility of maintaining the right version is shifted from the Developer to Spring Boot.
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


## `application.properties`

```java
# It is just a plain old *Java Properties* file.
# No different/special for Spring, except that the IDE offers a context sensitive help
# for the keys.
#
# Any changes we make in this file requires a Server Restart.
# The properties file will be read ONLY ONCE during the Bootstrap/Starting phase.
server.port=9001

# ViewResolver component
spring.mvc.view.prefix=/WEB-INF/views/
spring.mvc.view.suffix=.jsp
```

## Spring Boot - Rest

No different from the Spring MVC REST, except the JSON representation comes by default, due to the fact that *Jackson* API is added automatically by the starter parent `springboot-starter-web`!

## Spring Boot - Web MVC

It is also no different from Spring MVC, except the following.

### Tomcat Embed Jasper - JSP  Dependency

Though the Spring Boot has Tomcat as a default Embedded Container, it does *NOT* have a support for the *JSP* as a View Technology in _MVC_.

We need to add the `tomcat-embed-jasper` dependency manually in the `pom.xml` file, as follows.

```xml
<!-- https://mvnrepository.com/artifact/org.apache.tomcat.embed/tomcat-embed-jasper -->
<dependency>
  <groupId>org.apache.tomcat.embed</groupId>
  <artifactId>tomcat-embed-jasper</artifactId>
  <!--<version>11.0.0-M4</version>-->
</dependency>
```

> *Note*: The version is NOT required or recommended when using it along with Spring Boot, as it automatically downloads the right and compatible version of the library along with the other relevant ones.

### JSP Files to be placed

We need to have all the `.jsp` files inside the following structure.

> *Note*: We should first create the folders `/webapp/WEB-INF/views` in the directory `/src/main`

```
/src/main/webapp/WEB-INF/<folders>/*.jsp
```

*Example*:

```
/src/main/webapp/WEB-INF/views/index.jsp
```

### View Resolver

We need to add the View Resolver component attributes in the `application.properties` as follows.


> *NOTE*: Upto `/src/main/webapp` will be considered automatically.

```java
spring.mvc.view.prefix=/WEB-INF/views/
spring.mvc.view.suffix=.jsp
```

> *NOTE*: It is exactly the same way we configured as a `<bean>` element in the `registration-servlet.xml` for the *spring-mvc-demo* project to add the `InternalViewResolver`

### Controller

1. You should have a class marked with `@Controller` (and NOT `@RestController`)

2. Add a method marked with `@GetMapping` annotation that returns a `String` which is nothig but the logical view name.

The Spring MVC internally computes the full path to the JSP file - with the help of the `ViewResolver` - what we configured now in the `application.properties` by picking up the `prefix`, and `suffix`.

```java
@Controller
public class HelloController
{
  @GetMapping("/")
  public String index()
  {
    return "index";
  }
}
```

> *Note*. Upon hitting the URL `http://localhost:8080/`, Spring MVC will transfer the control to the `index()` method of the `HelloController` and picks up the return value  as a String - `index`, and then it completes the full path to the JSP page - `/webapp/WEB-INF/views/index.jsp' with the help of the View Resolver.

## Interesting scenario

What if we have a same URI mapped to two different methods ? - `http://localhost:8080/`

```java
@Controller
public class HelloController
{
  @GetMapping("/")
  public String index()
  {
    return "index";
  }
}
```

```java
@RestController
public class HelloRestController
{
  @GetMapping("/")
  public String sayHello()
  {
    return "Hello from SpringBoot Rest";
  }
}
```

*Issue* :

Spring cannot decide which one to map for, as it is now an *ambiguous* mapping!

*Solution*

We need to decide which one to be mapped for the URI pattern - Web Controller or the Rest Controller?

We would prefer to let the `@Controller` take over the responsibility of handling the `http://localhost:8080/` - URI endpoint, than the `@RestController`

*Reason*:

1. Ideally, the URI `http://localhost:8080/`  does *NOT* give any clue or other parts in the URI, it just has a "/". In this case, it will be the good starting point for a Web Controller to show the *Home Page*.

2. For the `@RestController`, we would prefer following the best practices OR the convention of prefixing the URIs for the Rest with `/api/`.

As the `/api` prefix is missing in this URI, the `@Controller` is deemed a best choice.

> *Note*: Legally, any of the controllers can take up this request. There is no hard and fast rule the ONLY a Web Controller can respond to the URIs ending with just a "/", and it is just a commonly agreed practice and the  strategy.

# JPA Vs JDBC

* JDBC - Java Database Connectivity
* JPA - Java Persistence API

*JDBC*

Using a *Driver* class, we would perform the step by step activities to talk to the database and manipulate the data. JDBC Program is an example.

*JPA*

Java Persistence API is a Specification that helps us achieve the data manipulation into the permanent storage (Database) which is *non-volatile* (sustains the reboots!), via ORM - *Object Relational Mapping*.

> *NOTE* :
> * `Volatile` - means Temporary. The data stored in the   
variables of a Program will cease to exist, once the
Program execution completes.
> * `Non-Volatile`: means Permanent - the data stored in a
File System or a Database which will sustain the reboots
of an Applicaiton or the Server, as the data is not
stored inside the application, but outside of it.

*ORM*

`ORM` (Object Relational Mapping) is a technique by which we can associate or `map` the information stored in the Database Table - typically we speak about *RDBMS* (`Relation`al Database Managemet System) to the `Object`s in Java.

* Object
* Relational
* Mapping

It is a simplified mechanism to ease the Java developers, as they can still continue to be on the Java world, than stepping out to the different world - RDBMS - while dealing with the Databases that are of Relational in nature (RDBMS).

*ORM* Vs *JDBC*

* JDBC lets us talk to the Database via `java.sql` package through the Interfaces (most part) and a few classes - `Connection`, `DriverManager`, `Statement`, `ResultSet` and `SQLException` etc., and we write a lot of Java executable statements to establish a connection and initiate a transaction with the Database, while catching the exceptions thrown if any by the *JDBC Driver*.

> *NOTE*: The JDBC Driver is specific to the Database. We had `mysql-connector-v-x.y.z.jar` for the MySQL Database in a Java Program.

* ORM lets us talk to the Database via plain Java objects, and NOT through the JDBC!

```java
Person p = new Person("Rama", "Vemulapalli", 26);
personDAO.save(p); //this line does the *magic* of storing the Domain object into the Table.
```

*JPA* Vs *ORM*

* ORM  - let us use Java Objects
* JPA - uses ORM and a *Repository* pattern to have a common set of methods which are frequenly used by a Developer for the *CRUD* operations.

# References

* Application Properties - Official Reference - [https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html)
