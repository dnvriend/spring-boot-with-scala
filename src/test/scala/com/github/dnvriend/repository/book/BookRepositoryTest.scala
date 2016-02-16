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

import com.github.dnvriend.SpringConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import scala.collection.JavaConversions._

@ContextConfiguration(classes = Array[Class[_]](classOf[SpringConfiguration]))
class BookRepositoryTest extends TestSpec {

  // it is blocking as f.. but hey, its all generated!
  // so for some use cases, this can be great!

  @Autowired
  var bookRepository: BookRepository = null

  it should "save a book" in {
    val book = Book("foo", "bar")
    val id = bookRepository.save(book).id
    bookRepository.findOne(id) shouldBe book
  }

  it should "save a number of entities" in {
    val xs = (1 to 10).map(i ⇒ Book(i.toString, i.toString))
    bookRepository.save(xs)
    bookRepository.count() shouldBe 10
  }

  it should "find by reader" in {
    val xs = (1 to 10).map(i ⇒ Book("foo", i.toString))
    val xy = (1 to 20).map(i ⇒ Book("bar", i.toString))
    bookRepository.save(xs ++ xy)
    bookRepository.count() shouldBe 30
    bookRepository.findByReader("foo").size shouldBe 10
    bookRepository.findByReader("bar").size shouldBe 20
  }

  override protected def beforeEach(): Unit = {
    bookRepository.deleteAll()
  }
}
