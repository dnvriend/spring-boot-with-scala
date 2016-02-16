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

package com.github.dnvriend.akka

import akka.actor.{ Inbox, Props, ActorRef, ActorSystem }
import com.github.dnvriend.counting.actor.CountingActor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.{ Bean, Configuration }

@Configuration
class ActorsConfig {

  @Autowired
  val system: ActorSystem = null

  @Autowired
  val applicationContext: ApplicationContext = null

  @Bean
  def countingActorRef: ActorRef =
    system.actorOf(Props(applicationContext.getBean("countingActor").asInstanceOf[CountingActor]))

  @Bean
  def inbox: Inbox = Inbox.create(system)
}
