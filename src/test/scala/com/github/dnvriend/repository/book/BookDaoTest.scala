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

@ContextConfiguration(classes = Array[Class[_]](classOf[SpringConfiguration]))
class BookDaoTest extends TestSpec {

  @Autowired
  val dao: BookDao = null

  it should "save" in {
    dao.save(Book("", "")).toTry should be a 'success
  }

  it should "find one" in {
    val id = dao.save(Book("", "")).futureValue.id
    dao.findOne(id).futureValue should not be 'empty
  }
}
