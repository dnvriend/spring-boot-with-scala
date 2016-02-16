# spring-boot-starter-data-rest-scala
A small study project on how to use spring-boot-starter-data-rest

# TL;DR
With [spring-boot-starter-data-rest](http://docs.spring.io/spring-data/rest/docs/2.4.2.RELEASE/reference/html/) 
you can create hypermedia driven REST services with virtually no effort. 

Service | Status | Description
------- | ------ | -----------
License | [![License](http://img.shields.io/:license-Apache%202-red.svg)](http://www.apache.org/licenses/LICENSE-2.0.txt) | Apache 2.0

# Calling the web service
Service | Verb | [httpie](https://github.com/jkbrzt/httpie) | Description
--------|------|--------|-------------
books    | GET  | http -v get localhost:8080/api/books | Get a list of books
books    | POST | http -v post localhost:8080/api/books reader=foo isbn=bar id=0 | Create a book
books/{id} | GET | http -v get localhost:8080/api/books/1 | Get a book by id
books/{id}| DELETE | http -v delete localhost:8080/books/1 | Delete a book by id
books | DELETE | http -v delete localhost:8080/api/books | Delete all books
persons | POST | http post localhost:8080/api/persons firstName=dennis lastName=vriend birthDate=1974-11-01 married=false | Create a person
persons/search | GET | http -v localhost:8080/api/persons/search/findByLastNameIgnoreCase?lastName=dennis | search for persons 
persons/search | GET | http -v localhost:8080/api/persons/search/findByFirstNameIgnoreCase?firstName=dennis | search for persons
persons/search | GET | http -v localhost:8080/api/persons/search/findByFirstNameOrLastName firstName==dennis lastName==vriend | search for persons

# The HAL browser
[Hypertext Application Language](http://stateless.co/hal_specification.html) or HAL is a simple format that gives a 
consistent and easy way to hyperlink between resources in your API. The HAL browser (a webapp) is available at http://localhost:8080/api.
The HAL browser can be used use it to navigate the app and create new resources. For more information read the [HAL browser paragraph
from the spring data rest reference documentation](http://docs.spring.io/spring-data/rest/docs/2.4.2.RELEASE/reference/html/#_the_hal_browser).

# Resources
- [Spring Data REST](http://docs.spring.io/spring-data/rest/docs/2.4.2.RELEASE/reference/html/)
- [Spring JPA](http://docs.spring.io/spring-data/jpa/docs/1.9.2.RELEASE/reference/html/)
- [Common application properties](https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html)

# Query creation
Generally the queryhttp://localhost:8080/api/browser/index.html#/api creation mechanism for JPA works as described in [Query](http://docs.spring.io/spring-data/jpa/docs/1.9.2.RELEASE/reference/html/#repositories.query-methods) methods. 
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

Have fun!