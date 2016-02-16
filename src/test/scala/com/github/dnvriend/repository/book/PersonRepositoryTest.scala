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
import com.github.dnvriend.repository.{ Address, Person, PersonRepository }
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import scala.collection.JavaConversions._

@ContextConfiguration(classes = Array[Class[_]](classOf[SpringConfiguration]))
class PersonRepositoryTest extends TestSpec {

  @Autowired
  var repo: PersonRepository = null

  val person = Person("foo", "bar", dateFromString("1980-01-01"), Address("1000AA", "1"))

  def pfPerson: PartialFunction[Any, _] = {
    case Person("foo", "bar", _, _, _) ⇒
  }

  it should "findByFirstNameIgnoreCase" in {
    repo.findByFirstNameIgnoreCase("FOO", pageRequest).head should matchPattern(pfPerson)
  }

  it should "findByLastNameIgnoreCase" in {
    repo.findByLastNameIgnoreCase("BAR", pageRequest).head should matchPattern(pfPerson)
  }

  it should "findByFirstNameOrLastName" in {
    repo.findByFirstNameOrLastName("foo", "bar", pageRequest).head should matchPattern(pfPerson)
  }

  it should "findByFirstNameOrLastNameIgnoreCase" in {
    repo.findByFirstNameOrLastNameIgnoreCase("FOO", "BAR", pageRequest).head should matchPattern(pfPerson)
  }

  it should "findByBirthDate" in {
    repo.findByBirthDate(dateFromString("1980-01-01"), pageRequest).head should matchPattern(pfPerson)
  }

  override protected def beforeEach(): Unit = {
    repo.deleteAll()
    repo.save(person)
  }
}
