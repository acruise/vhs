package com.cluonflux.vhs.server

import akka.actor._
import akka.io.IO
import spray.can.Http
import spray.http._

final class VhsHttpService extends Actor with ActorLogging {
  IO(Http)(context.system) ! Http.Bind(self, "", 8080)

  override def receive: Receive = {
    case Http.Bound(localAddr) =>
      log.info("Bound to {}", localAddr)

    case Http.Connected(remote, local) =>
      val connection = sender()
      connection ! Http.Register(context.actorOf(Props(new VhsHttpConnection(connection))))
  }
}

final class VhsHttpConnection(connection: ActorRef) extends Actor with ActorLogging {
  context.watch(connection)

  override def receive: Receive = {
    case HttpRequest(HttpMethods.GET, getUri, getHeaders, _, getProto) =>
      sender() ! HttpResponse(StatusCodes.OK, HttpEntity(ContentTypes.`text/plain`, "Yo!"))

    case Terminated(`connection`) =>
      log.info("Connection closed")
      context.stop(self)
  }
}