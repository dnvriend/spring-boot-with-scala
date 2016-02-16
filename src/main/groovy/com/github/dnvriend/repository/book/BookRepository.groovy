package com.github.dnvriend.repository.book

import com.github.dnvriend.repository.book.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByReader(String reader)
}
