lazy val scalaTestVersion = "3.0.4"
lazy val scalacticVersion = "3.0.4"
lazy val dispatchVersion = "0.13.2"

name := "esa-scala"
version := "0.1"
scalaVersion := "2.12.1"

libraryDependencies += "net.databinder.dispatch" %% "dispatch-core" % dispatchVersion
libraryDependencies += "org.scalactic" %% "scalactic" % scalacticVersion % "test"
libraryDependencies += "org.scalatest" %% "scalatest" % scalaTestVersion % "test"
