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

import akka.actor.{ActorRef, ActorSystem, Inbox}
import com.github.dnvriend.counting.actor.CountingActor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation._

import scala.beans.BeanProperty
import scala.concurrent.duration._

case class CountResult(@BeanProperty count: Int, @BeanProperty status: String)

@RestController
@RequestMapping(path = Array("/api/count"))
class CountingController {

  @Autowired
  val system: ActorSystem = null

  @Autowired
  val inbox: Inbox = null

  @Autowired
  val countingActor: ActorRef = null

  @RequestMapping(method = Array(RequestMethod.GET))
  def get: CountResult = {
    inbox.send(countingActor, CountingActor.Get)
    inbox.receive(5.seconds) match {
      case e: Int ⇒ CountResult(e, "COUNT")
      case e      ⇒ CountResult(0, "Unknown type: " + e.getClass.getName)
    }
  }

  @RequestMapping(method = Array(RequestMethod.POST))
  def count: CountResult = {
    inbox.send(countingActor, CountingActor.Count)
    inbox.receive(5.seconds) match {
      case e: Int ⇒ CountResult(e, "COUNT")
      case e      ⇒ CountResult(0, "Unknown type: " + e.getClass.getName)
    }
  }
}
