# spring-boot-readinglist
A small study project on [Spring-Boot](http://projects.spring.io/spring-boot/) with [Groovy](http://www.groovy-lang.org/).

# Building and running
- Sometimes you have to remove the `gradle` and `build` directories: `rm -rf build gradle`
- To see a list of available tasks: `gradle tasks`
- To run the example: `gradle bootRun`
- To build an executable jar: `gradle bootRepackage`, the jar is available at `build/libs` and can be executed with `ava -jar spring-boot-readinglist-0.0.1-SNAPSHOT.jar`.

