package com.eskimi.eskimibid.web
package service

import akka.actor.{ ActorSystem, Props }
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer

import com.eskimi.eskimibid._

import core.util.{ EskimiConfig, EskimiLog, ApplicationLifecycle }

class Application extends ApplicationLifecycle with EskimiLog {

  private[this] var started: Boolean = false

  private val applicationName = "eskimi-web"
  implicit val system         = ActorSystem(s"$applicationName-system")

  def start() {
    log.info(s"Starting $applicationName Service")

    if (!started) {

      implicit val materializer = ActorMaterializer()

      Http().bindAndHandle(
        new EskimiPublicWebServiceT {
          override def actorRefFactory = system
        }.route,
        EskimiConfig.publiclWebInterface,
        EskimiConfig.publiclHttpPort
      )

      started = true
    }
  }

  def stop() {
    log.info(s"Stopping $applicationName Service")

    if (started) {
      started = false
      system.terminate()
    }
  }

}
