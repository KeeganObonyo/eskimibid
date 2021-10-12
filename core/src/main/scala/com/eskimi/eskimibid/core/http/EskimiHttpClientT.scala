package com.eskimi.eskimibid.core
package http

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ HttpRequest, StatusCode }
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.ActorMaterializer

import com.eskimi.eskimibid._

import core.util.EskimiCCPrinter

case class EskimiHttpClientResponse(
  status: StatusCode,
  data: String
) extends EskimiCCPrinter

trait ATHttpClientT {

  implicit val system: ActorSystem

  final implicit lazy val materializer = ActorMaterializer()
  private lazy val http                = Http(system)

  def sendHttpRequest(req: HttpRequest): Future[EskimiHttpClientResponse] = for {
    response <- http.singleRequest(req)
    data     <- Unmarshal(response.entity).to[String]
  } yield EskimiHttpClientResponse(
    status = response.status,
    data   = data
  )
}
