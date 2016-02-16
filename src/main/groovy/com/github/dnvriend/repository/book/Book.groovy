package com.github.dnvriend.repository.book

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id
    String reader
    String isbn
    String title
    String author
    String description
}
