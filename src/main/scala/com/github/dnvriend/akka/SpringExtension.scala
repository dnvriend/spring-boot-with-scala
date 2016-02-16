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

import akka.actor.{Extension, ExtendedActorSystem, ExtensionIdProvider, ExtensionId}
import org.springframework.context.ApplicationContext

object SpringExtension extends ExtensionId[SpringExtensionImpl] with ExtensionIdProvider {
  override def createExtension(system: ExtendedActorSystem): SpringExtensionImpl =
    new SpringExtensionImpl(system)

  override def lookup(): ExtensionId[_ <: Extension] = SpringExtension
}

class SpringExtensionImpl(system: ExtendedActorSystem) extends Extension {
  var applicationContext: Option[ApplicationContext] = None
}
