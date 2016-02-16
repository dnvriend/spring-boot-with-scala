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

import java.util.concurrent.atomic.AtomicLong

import com.github.dnvriend.json.Greeting
import org.springframework.hateoas.mvc.ControllerLinkBuilder._
import org.springframework.http.HttpEntity
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(path = Array("/greeting"))
class GreetingController extends ControllerOps {

  val counter: AtomicLong = new AtomicLong()

  @RequestMapping(method = Array(RequestMethod.GET))
  def greeting(@RequestParam(value = "name", defaultValue = "World") name: String): HttpEntity[Greeting] = {
    val greeting = Greeting(s"Hello $name")
    greeting.add(linkTo(methodOn(classOf[GreetingController]).greeting(name)).withSelfRel())
    found(greeting)
  }
}
