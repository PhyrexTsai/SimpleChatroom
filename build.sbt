name := "SimpleChatroom"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= {
  val akkaVersion = "2.3.4"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion
  )
}

libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-compiler" % _ )