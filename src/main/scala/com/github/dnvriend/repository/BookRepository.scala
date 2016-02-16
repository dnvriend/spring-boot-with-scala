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

package com.github.dnvriend.repository

import javax.persistence.{ Entity, GeneratedValue, GenerationType, Id }

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param

import scala.beans.BeanProperty

@Entity
case class Book(
    @BeanProperty reader: String,
    @BeanProperty isbn: String,
    @ScalaJpaAnnotations.Id @ScalaJpaAnnotations.GeneratedValue(strategy = GenerationType.AUTO)@BeanProperty id: Long = 0L) {
  // default constructor for JPA
  def this() {
    this(null, null, 0L)
  }
}

/**
 * The BookRepository is blocking as f... but still, you get
 * so much for free, so for some use cases, this could be great!
 */
trait BookRepository extends JpaRepository[Book, java.lang.Long] {
  type Books = java.util.List[Book]

  def findByReaderIgnoreCase(@Param("reader") reader: String, pageable: Pageable): Books

  def findByIsbnIgnoreCase(@Param("isbn") isbn: String, pageable: Pageable): Books
}

