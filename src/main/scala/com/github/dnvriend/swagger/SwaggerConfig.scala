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

package com.github.dnvriend.swagger

import org.springframework.web.servlet.config.annotation.{ ResourceHandlerRegistry, WebMvcConfigurerAdapter, EnableWebMvc }
import springfox.documentation.builders.PathSelectors.regex
import org.springframework.context.annotation.{ Import, Bean, Configuration }
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableSwagger2
@Configuration
class SwaggerConfig {

  @Bean
  def newsApi: Docket = {
    new Docket(DocumentationType.SWAGGER_2)
      .groupName("greetings")
      .apiInfo(apiInfo)
      .select()
      .paths(regex("/api/greeting.*"))
      .build()
  }

  private def apiInfo: ApiInfo = {
    new ApiInfoBuilder()
      .title("Spring Data REST Sample with Swagger")
      .description("Spring Data REST Sample with Swagger")
      .termsOfServiceUrl("http://swagger.io/terms/")
      .contact("Dennis Vriend")
      .license("Apache License Version 2.0")
      .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
      .version("2.0")
      .build()
  }
}

@EnableWebMvc
@Import(value = Array(classOf[SwaggerConfig]))
class SwaggerUIConfig extends WebMvcConfigurerAdapter {
  override def addResourceHandlers(registry: ResourceHandlerRegistry): Unit = {
    registry.addResourceHandler("swagger-ui.html")
      .addResourceLocations("classpath:/META-INF/resources/")

    registry.addResourceHandler("/webjars/**")
      .addResourceLocations("classpath:/META-INF/resources/webjars/")
  }
}