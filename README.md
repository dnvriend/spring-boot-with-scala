# spring-boot-hypermedia-web-service #
[![License](http://img.shields.io/:license-Apache%202-red.svg)](http://www.apache.org/licenses/LICENSE-2.0.txt)

A small study project on how to use spring-boot-starter-hateos

# TL;DR
With [spring-boot](http://projects.spring.io/spring-boot/) you can create hypermedia driven REST services with ease.

# Calling the web service
Service | Verb | [httpie](https://github.com/jkbrzt/httpie) | Description
--------|------|--------|-------------
/Greeting | GET | http -v get localhost:8080/greeting?name=foo | returns a greeting
/books    | GET  | http -v get localhost:8080/books | Get a list of books
/books    | POST | http -v post localhost:8080/books reader=foo isbn=bar id=0 | Create a book
/books/{id} | GET | http -v get localhost:8080/books/1 | Get a book by id
/books/reader/{reader} | GET | http -v get localhost:8080/books/reader/dennis | Get a list of books by reader name  
/books/{id}| DELETE | http -v delete localhost:8080/books/1 | Delete a book by id
/books | DELETE | http -v delete localhost:8080/books | Delete all books

# Resources
- [Spring - Building a Hypermedia-Driven RESTful Web Service](https://spring.io/guides/gs/rest-hateoas/)
- [Spring HATEOS reference](http://docs.spring.io/spring-hateoas/docs/0.19.0.RELEASE/reference/html/)

Have fun!
