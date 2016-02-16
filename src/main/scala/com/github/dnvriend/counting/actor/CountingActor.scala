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

package com.github.dnvriend.counting.actor

import akka.actor.Status.Success
import akka.actor.{ Actor, ActorLogging }
import akka.event.LoggingReceive
import com.github.dnvriend.couting.service.CountingService
import com.github.dnvriend.spring.SpringAnnotations
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

object CountingActor {
  case object Count
  case object Get
}

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class CountingActor(@SpringAnnotations.Autowired countingService: CountingService) extends Actor with ActorLogging {
  import CountingActor._

  // for spring
  def this() {
    this(null)
  }

  var count: Int = 0

  override def receive: Receive = LoggingReceive {
    case Count ⇒
      count = countingService.increment(count)
      sender() ! Success(count)
    case Get ⇒
      sender() ! Success(count)
  }
}
