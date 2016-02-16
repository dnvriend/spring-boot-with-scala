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

package com.github.dnvriend

import javax.jms.ConnectionFactory

import org.apache.activemq.ActiveMQConnectionFactory
import org.apache.activemq.camel.component.ActiveMQComponent
import org.apache.camel.CamelContext
import org.apache.camel.component.jms.JmsConfiguration
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

/**
 * see: http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#beans-java
 */
@SpringBootApplication
class SpringConfiguration {

  @Bean
  def activemq(context: CamelContext): ActiveMQComponent = {
    val component = context.getComponent("activemq").asInstanceOf[ActiveMQComponent]
    component.setConfiguration(jmsConfiguration)
    component.setTransacted(true)
    component
  }

  @Bean
  def connectionFactory: ConnectionFactory =
    new ActiveMQConnectionFactory("admin", "adminactivemq", "nio://boot2docker:61616")

  @Bean
  def jmsConfiguration: JmsConfiguration = {
    val jmsConfiguration: JmsConfiguration = new JmsConfiguration()
    jmsConfiguration.setConnectionFactory(connectionFactory)
    jmsConfiguration
  }
}

object Launch extends App {
  SpringApplication.run(classOf[SpringConfiguration], args: _*) // bootstrap the application
}
