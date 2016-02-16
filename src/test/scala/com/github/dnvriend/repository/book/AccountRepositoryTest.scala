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
import com.github.dnvriend.repository.{ Account, AccountRepository }
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import scala.collection.JavaConversions._

@ContextConfiguration(classes = Array[Class[_]](classOf[SpringConfiguration]))
class AccountRepositoryTest extends TestSpec {

  @Autowired
  val repo: AccountRepository = null

  val account: Account = Account("foo", "bar")

  val pfAccount: PartialFunction[Any, _] = {
    case Account("foo", "bar", _) ⇒
  }

  it should "findByUsername" in {
    repo.findByUsername("foo") should matchPattern(pfAccount)
  }

  it should "findAll" in {
    repo.findAll() should not be 'empty
  }

  override protected def beforeEach(): Unit = {
    repo.deleteAll()
    repo.save(account)
  }
}
