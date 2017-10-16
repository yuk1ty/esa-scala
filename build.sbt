name := "esa-scala"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies += "org.skinny-framework" %% "skinny-http-client" % "2.5.0"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
libraryDependencies += "org.eclipse.jetty" % "jetty-server" % "9.4.7.v20170914"
libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.25"