name := "chatbot"

version := "0.1"

scalaVersion := "2.11.6"


resolvers += "scalac repo" at "https://raw.githubusercontent.com/ScalaConsultants/mvn-repo/master/"

libraryDependencies +=  "org.scalaj" %% "scalaj-http" % "2.4.2"
libraryDependencies +=  "com.lihaoyi" %% "upickle" % "0.7.1"

libraryDependencies ++= Seq("io.scalac" %% "slack-scala-bot-core" % "0.2.1")