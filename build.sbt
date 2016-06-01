import AssemblyKeys._

assemblySettings

name := "jiggJsonIO"

version := "0.0.0"

lazy val root = (project in file("."))

scalaVersion := "2.11.7"

mainClass in assembly := Some("jiggJsonIO.Main")

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.11" % "test",
  "com.novocode" % "junit-interface" % "0.10-M4" % "test->default",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test",
  "org.scala-lang.modules" %% "scala-xml" % "1.0.5",
  "org.json4s" %% "json4s-native" % "3.3.0",
  "org.json4s" %% "json4s-jackson" % "3.3.0"
)
