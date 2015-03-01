package com.cluonflux.vhs.server

import akka.actor._

object ServerMain {
  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("vhs-server")

    val httpService = system.actorOf(Props(new VhsHttpService()))
  }
}



