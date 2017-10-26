lazy val slf4jApiVersion = "1.7.25"
lazy val skinnyHttpClientVersion = "2.5.1"
lazy val scalaTestVersion = "3.0.4"
lazy val scalacticVersion = "3.0.4"
lazy val playWsVersion = "1.1.2"

name := "esa-scala"
version := "0.1"
scalaVersion := "2.12.1"
dependencyOverrides += "org.slf4j" % "slf4j-api" % slf4jApiVersion

libraryDependencies += "org.skinny-framework" %% "skinny-http-client" % skinnyHttpClientVersion
libraryDependencies += "com.typesafe.play" %% "play-ahc-ws-standalone" % playWsVersion
libraryDependencies += "org.scalactic" %% "scalactic" % scalacticVersion % "test"
libraryDependencies += "org.scalatest" %% "scalatest" % scalaTestVersion % "test"
