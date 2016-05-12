import AssemblyKeys._

assemblySettings

name := "comainu-scala"

version := "0.0.0"

lazy val root = (project in file("."))

scalaVersion := "2.11.7"

mainClass in assembly := Some("comainuScala.Main")

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.11" % "test",
  "com.novocode" % "junit-interface" % "0.10-M4" % "test->default",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test"
)
