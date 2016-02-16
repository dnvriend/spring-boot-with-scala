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
import org.springframework.hateoas.Link
import org.springframework.hateoas.mvc.ControllerLinkBuilder._
import org.springframework.http.{ HttpEntity, MediaType, ResponseEntity }
import org.springframework.web.bind.annotation._
import scala.collection.JavaConversions._

import scala.util.Try

@RestController()
@RequestMapping(path = Array("/books"))
class BookController extends ControllerOps {

  @Autowired
  val bookRepository: BookRepository = null

  def addLinks(book: Book): Book = {
    book.add(linkTo(methodOn(classOf[BookController]).getBook(book.bookId)).withSelfRel())
    book.add(linkTo(methodOn(classOf[BookController]).findByReader(book.reader)).withRel("reader"))
    book.add(linkTo(methodOn(classOf[BookController]).deleteBook(book.bookId)).withRel("delete"))
    book.add(linkTo(classOf[BookController]).withRel("books"))
    book
  }

  def addLinksAsEntity(book: Book): HttpEntity[Book] = found(addLinks(book))

  @RequestMapping(path = Array("/{id}"), method = Array(RequestMethod.GET))
  def getBook(@PathVariable("id") id: Long): HttpEntity[Book] =
    Option(bookRepository.findOne(id)).map(addLinksAsEntity).getOrElse(notFound)

  @RequestMapping(method = Array(RequestMethod.GET))
  def getAllBooks: HttpEntity[java.util.List[Book]] =
    found(bookRepository.findAll().map(addLinks))

  @RequestMapping(path = Array("/reader/{reader}"), method = Array(RequestMethod.GET))
  def findByReader(@PathVariable("reader") reader: String): HttpEntity[java.util.List[Book]] =
    found(bookRepository.findByReader(reader).map(addLinks))

  @RequestMapping(method = Array(RequestMethod.POST), consumes = Array(MediaType.APPLICATION_JSON_VALUE))
  def postBook(@RequestBody book: Book): HttpEntity[Book] =
    found(addLinks(bookRepository.save(book)))

  @RequestMapping(path = Array("/{id}"), method = Array(RequestMethod.DELETE))
  def deleteBook(@PathVariable("id") id: Long): ResponseEntity[Nothing] = {
    Try(bookRepository.delete(id))
    noContent
  }

  @RequestMapping(method = Array(RequestMethod.DELETE))
  def deleteAllBooks: ResponseEntity[Nothing] = {
    bookRepository.deleteAll()
    noContent
  }
}
