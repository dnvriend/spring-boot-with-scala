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

import io.swagger.annotations.{ ApiOperation, ApiImplicitParams, ApiImplicitParam, ApiResponses, ApiResponse }
import org.apache.camel.ProducerTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.{ RequestMapping, RequestMethod, RequestParam, RestController }

import scala.beans.BeanProperty

case class Greeting(@BeanProperty message: String)

@RestController
@RequestMapping(path = Array("/api/greeting"))
class GreetingController {

  @Autowired
  val producerTemplate: ProducerTemplate = null

  @ApiOperation(value = "getGreeting", nickname = "getGreeting")
  @ApiImplicitParams(value = Array(
    new ApiImplicitParam(name = "name", value = "User's name", required = false, dataType = "string", paramType = "query", defaultValue = "World")
  ))
  @ApiResponses(value = Array(
    new ApiResponse(code = 200, message = "Success", response = classOf[Greeting]),
    new ApiResponse(code = 401, message = "Unauthorized"),
    new ApiResponse(code = 403, message = "Forbidden"),
    new ApiResponse(code = 404, message = "Not Found"),
    new ApiResponse(code = 500, message = "Failure")
  ))
  @RequestMapping(method = Array(RequestMethod.GET), produces = Array(MediaType.APPLICATION_JSON_VALUE))
  def greeting(@RequestParam(value = "name", defaultValue = "World") name: String): Greeting =
    Greeting(s"Hello $name")

  @ApiImplicitParams(value = Array(
    new ApiImplicitParam(name = "name", value = "User's name", required = false, dataType = "string", paramType = "query", defaultValue = "World")
  ))
  @ApiResponses(value = Array(
    new ApiResponse(code = 200, message = "Success", response = classOf[Greeting]),
    new ApiResponse(code = 401, message = "Unauthorized"),
    new ApiResponse(code = 403, message = "Forbidden"),
    new ApiResponse(code = 404, message = "Not Found"),
    new ApiResponse(code = 500, message = "Failure")
  ))
  @ApiOperation(value = "getCamelDirectGreeting", nickname = "getCamelDirectGreeting")
  @RequestMapping(path = Array("/camel/direct"), method = Array(RequestMethod.GET), produces = Array(MediaType.APPLICATION_JSON_VALUE))
  def camelDirect(@RequestParam(value = "name", defaultValue = "World") name: String): Greeting =
    producerTemplate.requestBody("direct:helloworld", name) match {
      case msg: String ⇒ Greeting(msg)
      case e           ⇒ Greeting("Unknown type: " + e.getClass.getName)
    }

  @ApiImplicitParams(value = Array(
    new ApiImplicitParam(name = "name", value = "User's name", required = false, dataType = "string", paramType = "query", defaultValue = "World")
  ))
  @ApiResponses(value = Array(
    new ApiResponse(code = 200, message = "Success", response = classOf[Greeting]),
    new ApiResponse(code = 401, message = "Unauthorized"),
    new ApiResponse(code = 403, message = "Forbidden"),
    new ApiResponse(code = 404, message = "Not Found"),
    new ApiResponse(code = 500, message = "Failure")
  ))
  @ApiOperation(value = "getCamelActiveMqGreeting", nickname = "getCamelActiveMqGreeting")
  @RequestMapping(path = Array("/camel/activemq"), method = Array(RequestMethod.GET), produces = Array(MediaType.APPLICATION_JSON_VALUE))
  def camelActiveMQ(@RequestParam(value = "name", defaultValue = "World") name: String): Greeting =
    producerTemplate.requestBody("activemq:helloworld", name) match {
      case msg: String ⇒ Greeting(msg)
      case e           ⇒ Greeting("Unknown type: " + e.getClass.getName)
    }
}
