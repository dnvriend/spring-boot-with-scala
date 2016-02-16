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

package com.github.dnvriend.security

import com.github.dnvriend.repository.{ Account, AccountRepository }
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.{ Bean, Configuration }
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.{ EnableWebSecurity, WebSecurityConfigurerAdapter }
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.{ User, UserDetails, UserDetailsService, UsernameNotFoundException }

@Configuration
class SecurityConfig extends GlobalAuthenticationConfigurerAdapter {

  @Autowired
  val accountRepository: AccountRepository = null

  override def init(auth: AuthenticationManagerBuilder): Unit =
    auth.userDetailsService(userDetailsService)

  @Bean
  def userDetailsService: UserDetailsService = new UserDetailsService {
    override def loadUserByUsername(username: String): UserDetails = {
      Option(accountRepository.findByUsername(username)).map { account ⇒
        new User(account.username, account.password, true, true, true, true, AuthorityUtils.createAuthorityList("USER"))
      }.getOrElse(throw new UsernameNotFoundException(s"could not find the user: $username'"))
    }
  }

  @Bean
  def init: CommandLineRunner = new CommandLineRunner {
    override def run(args: String*): Unit = {
      accountRepository.save(Account("foo", "bar"))
    }
  }
}

@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  override def configure(http: HttpSecurity): Unit = {
    http.authorizeRequests().anyRequest().fullyAuthenticated().and()
      .httpBasic().and().csrf().disable()
  }
}
