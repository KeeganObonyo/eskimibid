package com.eskimi.eskimibid.core
package test

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

import akka.http.scaladsl.model._
import akka.http.scaladsl.unmarshalling.Unmarshal

import akka.stream.ActorMaterializer

import com.eskimi.eskimibid._

import core.http._

trait EskimibidTestHttpServiceT extends EskimibidCoreTestServiceT {

  final implicit val materializer = ActorMaterializer()

  def getHttpResponse(req: HttpRequest) = {
    val dataFut = Unmarshal(req.entity).to[String]
    getHttpResponseImpl(Await.result(dataFut, 1.second))
  }

  private var httpResponses: List[EskimiHttpClientResponse] = Nil
  def setHttpResponses(responses: List[EskimiHttpClientResponse]) = {
    httpResponses = responses
  }

  private def getHttpResponseImpl(data: String): EskimiHttpClientResponse = {
    httpResponses match {
      case x::xs =>
        httpResponses = xs
        x
      case Nil   =>
        throw new Exception("No Http responses for testcase that expects some")
    }
  }
}
