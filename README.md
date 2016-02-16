# camel-spring-boot-test
A small study project on how to use [camel-spring-boot](http://camel.apache.org/spring-boot.html) with Scala and Akka. 
It focusses on several spring boot starter poms and application types. It uses branches for the separate project types.

# IntelliJ IDEA support
IntelliJ IDEA has spring-boot support. You should enable the Spring Boot plugin and add a spring project facet and add
the Application Context and Configuration files to the facet. Please view the following video for more information: [Youtube - Spring Developer - Spring Boot is made for tooling](https://www.youtube.com/watch?v=IHZ0d3MBb0g)

# Videos
- [Youtube - Spring Developer - Webinar: Spring Boot -- Simplifying Spring for Everyone](https://www.youtube.com/watch?v=D6nJSyWB-xA)
- [Youtube - Spring Developer - Spring Boot is made for tooling](https://www.youtube.com/watch?v=IHZ0d3MBb0g)
- [Youtube - Spring Developer - Documenting RESTful APIs](https://www.youtube.com/watch?v=k5ncCJBarRI)
- [Youtube - Spring Developer - Spring Data REST - Data Meets Hypermedia + Security](https://www.youtube.com/watch?v=s9Cd3-0gYKA)
- [Youtube - Spring Developer - The State of Securing RESTful APIs with Spring](https://www.youtube.com/watch?v=o4nt9IR8iL8)
- [Youtube - Spring Developer - Introduction to Reactive Programming](https://www.youtube.com/watch?v=fec9nEIybp0)
- [Youtube - Spring Developer - Introducing RxJava into a Spring Boot REST API](https://www.youtube.com/watch?v=QOR69q1e63Y)
- [Youtube - Spring Developer - Boot your search with Spring](https://www.youtube.com/watch?v=rf3aQRYxLBs)
- [Youtube - Spring Developer - Spring XD - A Guided Tour](https://www.youtube.com/watch?v=lteee9N816k)
- [Youtube - Spring Developer - Spring XD today and tomorrow](https://www.youtube.com/watch?v=NIBYST0z3sg)
- [Youtube - Spring Developer - Reactive data-pipelines with Spring XD and Kafka](https://www.youtube.com/watch?v=nP7Cx4yeZU4)
- [Youtube - Spring Developer - Stream Processing at Scale with Spring XD and Kafka](https://www.youtube.com/watch?v=OZCQ52H0Kks)
- [Youtube - Spring Developer - Webinar: Spring Integration 4.0 - The New Frontier](https://www.youtube.com/watch?v=g3DgdSqEgzI)
- [Youtube - Spring Developer - Get the Most out of Testing with Spring 4.2](https://www.youtube.com/watch?v=enDXm12nVPc)

# Spring boot starter poms
Starter POMs (libraries) are a set of convenient dependency descriptors that you can include in your `build.sbt`. 
You get a one-stop-shop for all the Spring and related technology that you need, without having to hunt through sample 
code and copy paste loads of dependency descriptors. For example, if you want to get started using Spring and 
JPA for database access, just include the `spring-boot-starter-data-jpa` dependency in your project, and you are good to go.

The following application starters are provided by Spring Boot:

- __spring-boot-starter:__ The core Spring Boot starter, including auto-configuration support, logging and YAML.
- __spring-boot-starter-actuator:__ Production ready features to help you monitor and manage your application.
- __spring-boot-starter-amqp:__ Support for the `Advanced Message Queuing Protocol` via spring-rabbit.
- __spring-boot-starter-aop:__ Support for aspect-oriented programming including spring-aop and AspectJ.
- __spring-boot-starter-artemis:__ Support for `Java Message Service API` via Apache Artemis.
- __spring-boot-starter-batch:__ Support for `Spring Batch` including HSQLDB database.
- __spring-boot-starter-cache:__ Support for Spring’s Cache abstraction.
- __spring-boot-starter-cloud-connectors:__ Support for `Spring Cloud Connectors` which simplifies connecting to services in cloud platforms like Cloud Foundry and Heroku.
- __spring-boot-starter-data-elasticsearch:__ Support for the Elasticsearch search and analytics engine including `spring-data-elasticsearch`.
- __spring-boot-starter-data-gemfire:__ Support for the GemFire distributed data store including `spring-data-gemfire`.
- __spring-boot-starter-data-jpa:__ Support for the `Java Persistence API` including `spring-data-jpa`, spring-orm and Hibernate.
- __spring-boot-starter-data-mongodb:__  Support for the MongoDB NoSQL Database, including `spring-data-mongodb`.
- __spring-boot-starter-data-rest:__ Support for exposing Spring Data repositories over REST via `spring-data-rest-webmvc`.
- __spring-boot-starter-data-solr:__ Support for the Apache Solr search platform, including spring-data-solr.
- __spring-boot-starter-freemarker:__ Support for the FreeMarker templating engine.
- __spring-boot-starter-groovy-templates:__ Support for the Groovy templating engine.
- __spring-boot-starter-hateoas:__ Support for HATEOAS-based RESTful services via spring-hateoas.
- __spring-boot-starter-hornetq:__ Support for `Java Message Service API` via HornetQ.
- __spring-boot-starter-integration:__ Support for common `spring-integration` modules.
- __spring-boot-starter-jdbc:__ Support for JDBC databases.
- __spring-boot-starter-jersey:__ Support for the Jersey RESTful Web Services framework.
- __spring-boot-starter-jta-atomikos:__ Support for JTA distributed transactions via Atomikos.
- __spring-boot-starter-jta-bitronix:__ Support for JTA distributed transactions via Bitronix.
- __spring-boot-starter-mail:__ Support for javax.mail.
- __spring-boot-starter-mobile:__ Support for spring-mobile.
- __spring-boot-starter-mustache:__ Support for the Mustache templating engine.
- __spring-boot-starter-redis:__ Support for the REDIS key-value data store, including spring-redis.
- __spring-boot-starter-security:__ Support for spring-security.
- __spring-boot-starter-social-facebook:__ Support for spring-social-facebook.
- __spring-boot-starter-social-linkedin:__ Support for spring-social-linkedin.
- __spring-boot-starter-social-twitter:__ Support for spring-social-twitter.
- __spring-boot-starter-test:__ Support for common test dependencies, including JUnit, Hamcrest and Mockito along with the `spring-test` module.
- __spring-boot-starter-thymeleaf:__ Support for the Thymeleaf templating engine, including integration with Spring.
- __spring-boot-starter-velocity:__ Support for the Velocity templating engine.
- __spring-boot-starter-web:__ Support for full-stack web development, including Tomcat and `spring-webmvc`.
- __spring-boot-starter-websocket:__ Support for WebSocket development.
- __spring-boot-starter-ws:__ Support for Spring Web Services.

# Exclude or swap technical facets 
- __spring-boot-starter-logging:__ Import Spring Boot’s default logging framework (Logback).
- __spring-boot-starter-tomcat:__ Import Spring Boot’s default HTTP engine (Tomcat).
- __spring-boot-starter-jetty:__ Imports the Jetty HTTP engine (to be used as an alternative to Tomcat).
- __spring-boot-starter-log4j:__ Support the Log4J logging framework.
- __spring-boot-starter-undertow:__ Imports the Undertow HTTP engine (to be used as an alternative to Tomcat).

Have fun!