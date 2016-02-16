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

package com.github.dnvriend.repository.book

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait BookDao {
  def save(book: Book): Future[Book]
  def findOne(id: Long): Future[Option[Book]]
}

@Component
class SpringBookDao() extends BookDao {

  @Autowired
  val repo: BookRepository = null

  // can use the repository in a semi-non-blocking way,
  // but we are still implementing methods.. #fail

  override def save(book: Book): Future[Book] =
    Future(repo.save(Book(book.reader, book.isbn)))

  override def findOne(id: Long): Future[Option[Book]] =
    Future(Option(repo.findOne(id)))
}
