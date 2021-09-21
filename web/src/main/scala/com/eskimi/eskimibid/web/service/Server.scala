package com.eskimi.eskimibid.web
package service

import com.eskimi.eskimibid._

import core.util.{ AbstractApplicationDaemon, EskimiApplicationT }

class ApplicationDaemon extends AbstractApplicationDaemon {
  def application = new Application
}

object ServiceApplication extends App with EskimiApplicationT[ApplicationDaemon] {
  def createApplication = new ApplicationDaemon
}
