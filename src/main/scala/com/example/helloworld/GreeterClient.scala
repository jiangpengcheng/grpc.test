package com.example.helloworld

import scala.util.Failure
import scala.util.Success

import akka.actor.ActorSystem
import akka.grpc.GrpcClientSettings
import akka.stream.ActorMaterializer

object GreeterClient {

  def main(args: Array[String]): Unit = {
    implicit val sys = ActorSystem("HelloWorldClient")
    implicit val mat = ActorMaterializer()
    implicit val ec = sys.dispatcher

    val grpcClientSettings =
      GrpcClientSettings.usingServiceDiscovery("distributed-scheduler")
    val client = GreeterServiceClient(grpcClientSettings)

    for (i <- 0 to 100) {
      singleRequestReply(s"test$i")
    }

    def singleRequestReply(name: String): Unit = {
      val reply = client.sayHello(HelloRequest(name))
      reply.onComplete {
        case Success(msg) =>
          println(msg)
        case Failure(e) =>
          println(s"Error: $e")
      }
    }
  }

}
