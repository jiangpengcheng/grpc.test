name := "grpc"

version := "0.1"

scalaVersion := "2.12.8"

lazy val akkaVersion = "2.5.19"
lazy val akkaGrpcVersion = "0.5.0"

enablePlugins(AkkaGrpcPlugin)

// ALPN agent
enablePlugins(JavaAgent)
javaAgents += "org.mortbay.jetty.alpn" % "jetty-alpn-agent" % "2.0.7" % "runtime;test"

libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % "test",
    "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)
