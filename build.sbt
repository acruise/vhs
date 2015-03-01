name := "vhs"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.5"
    
libraryDependencies := Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.9",
  "joda-time" % "joda-time" % "2.7",
  "org.joda" % "joda-convert" % "1.7",
  "io.spray" %% "spray-json" % "1.3.1",
  "io.spray" %% "spray-can" % "1.3.2",
  "io.spray" %% "spray-routing" % "1.3.2"
)

lazy val root = (project in file(".")).aggregate(model, server)

lazy val model = project

lazy val server = (project in file("project")).dependsOn(model)