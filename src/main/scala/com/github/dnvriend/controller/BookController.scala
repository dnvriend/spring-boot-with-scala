/*
 * Copyright 2016 Dennis Vriend
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.dnvriend.controller

import com.github.dnvriend.repository.book.{ Book, BookRepository }
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{ HttpStatus, MediaType, ResponseEntity }
import org.springframework.web.bind.annotation._

import scala.util.Try

@RestController
class BookController {

  @Autowired
  val bookRepository: BookRepository = null

  def notFound[A] = new ResponseEntity[A](HttpStatus.NOT_FOUND)
  def found[A](a: A) = new ResponseEntity[A](a, HttpStatus.OK)
  def noContent[A] = new ResponseEntity[A](HttpStatus.NO_CONTENT)

  @RequestMapping(path = Array("/books/{id}"), method = Array(RequestMethod.GET))
  def getBook(@PathVariable("id") id: Long): ResponseEntity[Book] =
    Option(bookRepository.findOne(id)).map(found).getOrElse(notFound)

  @RequestMapping(path = Array("/books"), method = Array(RequestMethod.GET))
  def getAllBooks: java.util.List[Book] =
    bookRepository.findAll()

  @RequestMapping(path = Array("/books/reader/{reader}"), method = Array(RequestMethod.GET))
  def findByReader(@PathVariable("reader") reader: String): java.util.List[Book] =
    bookRepository.findByReader(reader)

  @RequestMapping(path = Array("/books"), method = Array(RequestMethod.POST), consumes = Array(MediaType.APPLICATION_JSON_VALUE))
  def postBook(@RequestBody book: Book): Book =
    bookRepository.save(book)

  @RequestMapping(path = Array("/books/{id}"), method = Array(RequestMethod.DELETE))
  def deleteBook(@PathVariable("id") id: Long): ResponseEntity[Nothing] =
    Try(bookRepository.delete(id)).toOption.map(_ ⇒ noContent).getOrElse(noContent)

  @RequestMapping(path = Array("/books"), method = Array(RequestMethod.DELETE))
  def deleteAllBooks: ResponseEntity[Nothing] = {
    bookRepository.deleteAll()
    noContent
  }
}
