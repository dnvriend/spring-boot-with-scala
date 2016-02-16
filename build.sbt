name := "spring-boot-starter-data-rest"

organization := "com.github.dnvriend"

version := "1.0.0"

scalaVersion := "2.11.7"

resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/maven-releases/"

libraryDependencies ++= {
  val springBootVersion = "1.3.2.RELEASE"
  Seq(
    "org.scala-lang.modules" % "scala-java8-compat_2.11" % "0.7.0",
    "ch.qos.logback" % "logback-classic" % "1.1.2",
    "org.springframework.boot" % "spring-boot-starter-data-rest" % springBootVersion,
    "org.springframework.boot" % "spring-boot-starter-data-jpa" % springBootVersion,
    "org.springframework.boot" % "spring-boot-starter-logging" % springBootVersion,
    "org.springframework.data" % "spring-data-rest-hal-browser" % "2.4.2.RELEASE",
    "org.postgresql" % "postgresql" % "9.4-1206-jdbc42",
    "com.h2database" % "h2" % "1.4.191",
    "com.github.scalaspring" %% "scalatest-spring" % "0.2.1" % Test,
    "org.springframework.boot" % "spring-boot-starter-test" % springBootVersion % Test,
    "org.scalatest" %% "scalatest" % "2.2.4" % Test
  )
}

fork in Test := true

parallelExecution in Test := false

licenses +=("Apache-2.0", url("http://opensource.org/licenses/apache2.0.php"))

// enable scala code formatting //
import scalariform.formatter.preferences._

scalariformSettings

ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(AlignSingleLineCaseStatements.MaxArrowIndent, 100)
  .setPreference(DoubleIndentClassDeclaration, true)
  .setPreference(RewriteArrowSymbols, true)

// enable updating file headers //
import de.heikoseeberger.sbtheader.license.Apache2_0

headers := Map(
  "scala" -> Apache2_0("2016", "Dennis Vriend"),
  "conf" -> Apache2_0("2016", "Dennis Vriend", "#")
)

enablePlugins(AutomateHeaderPlugin, JavaAppPackaging)

// native packager
version in Docker := "latest"
maintainer in Docker := "dnvriend@gmail.com"
// use the 'java:8' docker base image
dockerBaseImage := "java:8"
// expose the HTTP port
dockerExposedPorts := Seq(8080)
dockerRepository := Option("dnvriend")
