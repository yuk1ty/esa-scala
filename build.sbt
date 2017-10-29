lazy val scalaTestVersion = "3.0.4"
lazy val scalacticVersion = "3.0.4"

name := "esa-scala"
version := "0.1"
scalaVersion := "2.12.2"

libraryDependencies += "net.databinder.dispatch" %% "dispatch-core" % "0.13.2"
libraryDependencies += "org.scalactic" %% "scalactic" % scalacticVersion % "test"
libraryDependencies += "org.scalatest" %% "scalatest" % scalaTestVersion % "test"
