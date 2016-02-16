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

package com.github.dnvriend.repository

import javax.persistence._

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.{ RepositoryRestResource, RestResource }

import scala.beans.BeanProperty

@Entity
case class Person(@BeanProperty firstName: String, @BeanProperty lastName: String, @BeanProperty @Temporal(value = TemporalType.DATE) birthDate: java.util.Date) {
  // default constructor for JPA
  def this() {
    this(null, null, null)
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @BeanProperty
  val id: Long = 0L

  @OneToOne(fetch = FetchType.EAGER, cascade = Array(CascadeType.ALL))
  @JoinColumn(name = "PERSON_ID")
  @BeanProperty
  var address: Address = null
}

@Entity
case class Address(@BeanProperty zipCode: String, @BeanProperty houseNumber: String) {
  def this() {
    this(null, null)
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @BeanProperty
  val id: Long = 0L
}

@RepositoryRestResource(path = "people")
trait PersonRepository extends JpaRepository[Person, java.lang.Long] {

  type People = java.util.List[Person]

  @RestResource(path = "names", rel = "names")
  def findByFirstNameIgnoreCase(@Param("firstName") firstName: String, pageable: Pageable): People
  def findByLastNameIgnoreCase(@Param("lastName") lastName: String, pageable: Pageable): People
  def findByFirstNameOrLastName(@Param(value = "firstName") firstName: String, @Param(value = "lastName") lastName: String, pageable: Pageable): People
  def findByFirstNameOrLastNameIgnoreCase(@Param(value = "firstName") firstName: String, @Param(value = "lastName") lastName: String, pageable: Pageable): People
  def findByBirthDate(@Param("birthDate") birthDate: java.util.Date, pageable: Pageable): People
  def findByAddressZipCode(@Param("zipCode") zipCode: String, pageable: Pageable): People
  def findByAddressHouseNumber(@Param("houseNumber") houseNumber: String, pageable: Pageable): People
  def findByAddressZipCodeAndAddressHouseNumber(@Param("zipCode") zipCode: String, @Param("houseNumber") houseNumber: String, pageable: Pageable): People
}
