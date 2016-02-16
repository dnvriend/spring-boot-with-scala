name := "spring-boot-starter-data-jpa-scala"

organization := "com.github.dnvriend"

version := "1.0.0"

scalaVersion := "2.11.7"

resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/maven-releases/"

libraryDependencies ++= {
  val springBootVersion = "1.3.2.RELEASE"
  Seq(
    "ch.qos.logback" % "logback-classic" % "1.1.2",
    "org.springframework.boot" % "spring-boot-starter-data-jpa" % springBootVersion,
    "org.springframework.boot" % "spring-boot-starter-logging" % springBootVersion,
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

enablePlugins(AutomateHeaderPlugin)