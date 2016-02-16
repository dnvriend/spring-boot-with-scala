# camel-spring-boot-test
A small study project on how to use [camel-spring-boot](http://camel.apache.org/spring-boot.html), register a camel route
that uses the [Camel Scala DSL](http://camel.apache.org/scala-dsl.html) to define the route.

# TL;DR
Spring Boot component provide auto-configuration for the Apache Camel. It auto-configures the Camel context and auto-detects 
Camel routes available in the Spring context and registers the key Camel utilities 
(like producer template, consumer template and the type converter) as beans.  

Service | Status | Description
------- | ------ | -----------
License | [![License](http://img.shields.io/:license-Apache%202-red.svg)](http://www.apache.org/licenses/LICENSE-2.0.txt) | Apache 2.0

# Calling the web service
Service | Verb | [httpie](https://github.com/jkbrzt/httpie) | Description
--------|------|--------|-------------
greeting | GET | http localhost:8080/api/greeting name==dennis | Returns a greeting
greeting/camel | GET | http -a foo:bar localhost:8080/api/greeting/camel/direct name==dennis | Uses a camel route to transform the body, returns the transformed body.
greeting/camel | GET | http -a foo:bar localhost:8080/api/greeting/camel/activemq name==dennis | Uses a camel route to transform the body, returns the transformed body.
books    | GET  | http -a foo:bar get localhost:8080/api/books | Get a list of books
books    | POST | http -a foo:bar post localhost:8080/api/books reader=foo isbn=bar id=0 | Create a book
books/{id} | GET | http -a foo:bar get localhost:8080/api/books/1 | Get a book by id
books/{id}| DELETE | http -a foo:bar delete localhost:8080/books/1 | Delete a book by id
books | DELETE | http -a foo:bar delete localhost:8080/api/books | Delete all books
people | POST |  http -a foo:bar post localhost:8080/api/people firstName=Dennis lastName=Vriend birthDate=1974-11-01 address:='{"zipCode":"1000AA","houseNumber":"33"}' | Create a person
people/search | GET | http -a foo:bar localhost:8080/api/people/search/findByLastNameIgnoreCase lastName==dennis | search for people 
people/search | GET | http -a foo:bar localhost:8080/api/people/search/findByFirstNameIgnoreCase firstName==dennis | search for people
people/search | GET | http -a foo:bar localhost:8080/api/people/search/findByFirstNameOrLastName firstName==dennis lastName==vriend | search for people

# Security
The web service is secured with Basic Authentication. The credentials are:

- username: foo
- password: bar

# The HAL browser
[Hypertext Application Language](http://stateless.co/hal_specification.html) or HAL is a simple format that gives a 
consistent and easy way to hyperlink between resources in your API. The HAL browser (a webapp) is available at http://localhost:8080/api.
The HAL browser can be used use it to navigate the app and create new resources. For more information read the [HAL browser paragraph
from the spring data rest reference documentation](http://docs.spring.io/spring-data/rest/docs/2.4.2.RELEASE/reference/html/#_the_hal_browser).

# Spring Actuator
[Spring Boot Actuator](https://github.com/spring-projects/spring-boot/tree/master/spring-boot-actuator) includes a number of additional 
features to help you monitor and manage your application when it’s pushed to production. You can choose to manage and monitor your 
application using HTTP endpoints. Auditing, health and metrics gathering can be automatically applied to your application. 
The [user guide](http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready) covers the features in more detail.

# Resources
- [Spring Boot Reference](http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Spring Boot Actuator Reference](http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready)
- [Spring Data REST Reference](http://docs.spring.io/spring-data/rest/docs/2.4.2.RELEASE/reference/html/)
- [Spring JPA](http://docs.spring.io/spring-data/jpa/docs/1.9.2.RELEASE/reference/html/)
- [Camel Spring Boot](http://camel.apache.org/spring-boot.html)
- [Securing REST APIs With Spring Boot](http://ryanjbaxter.com/2015/01/06/securing-rest-apis-with-spring-boot/)
- [Common application properties](https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html)

# Spring annotations
- __@SpringBootApplication:__ Indicates a `Configuration` class that declares one or more `@Bean` methods and also triggers 
`@EnableAutoConfiguration` and `@ComponentScan`. This is a convenience annotation that is equivalent to declaring @Configuration,
@EnableAutoConfiguration and @ComponentScan.
- __@Component:__  Indicates that an annotated class is a `component`. Such classes are considered as candidates for auto-detection 
when using annotation-based configuration and classpath scanning.
- __@Repository:__ specialization of `@Component`, Indicates that an annotated class is a `Repository`, originally defined by
Domain-Driven Design (Evans, 2003) as `a mechanism for encapsulating storage, retrieval, and search behavior which emulates a collection of objects`.
Teams implementing traditional [J2EE patterns](http://corej2eepatterns.com/index.htm) such as `Data Access Object` may also apply this stereotype to DAO classes, though care should be taken to
understand the distinction between Data Access Object and DDD-style repositories before doing so. This annotation is a general-purpose stereotype and 
individual teams may narrow their semantics and use as appropriate.
- __@Service:__ specialization of `@Component`, Indicates that an annotated class is a `Service`, originally defined by Domain-Driven
Design (Evans, 2003) as `an operation offered as an interface that stands alone in the model, with no encapsulated state.`
May also indicate that a class is a `Business Service Facade` (in the Core J2EE patterns sense), or something similar. 
- __@Controller:__ specialization of @Component, Indicates that an annotated class is a `Controller` (e.g. a web controller).
This annotation allows for implementation classes to be autodetected through classpath scanning. It is typically used in combination 
with annotated handler methods based on the `RequestMapping` annotation.

# Camel bean integration
Camel supports the integration of beans and POJOs. For more information: [Camel Bean Integration Reference](http://camel.apache.org/bean-integration.html).

- __@EndpointInject:__ To inject an endpoint, see more details at [POJO Producing](http://camel.apache.org/pojo-producing.html).
- __@BeanInject:__ To inject a bean obtained from the Registry. See [Bean Injection](http://camel.apache.org/bean-injection.html).
- __@PropertyInject:__ To inject a value using property placeholder.
- __@Produce:__ To inject a producer to send message to an endpoint. See [POJO Producing](http://camel.apache.org/pojo-producing.html).
- __@Consume:__ To inject a consumer on a method. See [POJO Consuming](http://camel.apache.org/pojo-consuming.html).
             
# JPA Template Query creation
Generally the query creation mechanism for JPA works as described in [Query](http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods) methods. 
Here’s a short example of what a JPA query method translates into:

```scala
trait UserRepository extends Repository[User, java.lang.Long] {
  def findByEmailAddressAndLastname(String emailAddress, String lastname): java.util.List[User]
}
```

We will create a query using the JPA criteria API from this but essentially this translates into the following query: 

```sql
select u from User u where u.emailAddress = ?1 and u.lastname = ?2
```

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