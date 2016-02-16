# camel-spring-boot-test
A small study project on how to use [camel-spring-boot](http://camel.apache.org/spring-boot.html). How to enable and 
configure Swagger for the Greeting service

Service | Status | Description
------- | ------ | -----------
License | [![License](http://img.shields.io/:license-Apache%202-red.svg)](http://www.apache.org/licenses/LICENSE-2.0.txt) | Apache 2.0

# Calling the web service
Service | Verb | [httpie](https://github.com/jkbrzt/httpie) | Description
--------|------|--------|-------------
swagger-ui | GET | http://localhost:8080/swagger-ui.html | Swagger UI: Credentials for the UI are: foo:bar 
swagger greeting | GET | http -a foo:bar localhost:8080/v2/api-docs/?group=greetings | get swagger doc for the greeting service
greeting | GET | http -a foo:bar localhost:8080/api/greeting name==dennis | Returns a greeting
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
- [Springfox (Swagger) Reference Documentation](http://springfox.github.io/springfox/docs/snapshot/#introduction)
- [Youtube - Spring Developer - Webinar: Documenting RESTful APIs](https://www.youtube.com/watch?v=knH5ihPNiUs)
- [Youtube - Devvox - Johannes Fiala - Generate client stubs & document your REST-API using Swagger & Spring](https://www.youtube.com/watch?v=43GhBbP--oI)

Have fun!