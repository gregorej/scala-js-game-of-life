scalaJSSettings

name := "Example"

version := "0.1-SNAPSHOT"

scalaVersion := "2.10.2"

ScalaJSKeys.persistLauncher := true

ScalaJSKeys.persistLauncher in Test := false

libraryDependencies ++= Seq(
    "org.scala-lang.modules.scalajs" %%% "scalajs-dom" % "0.6",
    "org.scala-lang.modules.scalajs" %% "scalajs-jasmine-test-framework" % scalaJSVersion % "test",
    "org.specs2" % "specs2_2.10" % "2.3.10"
)
