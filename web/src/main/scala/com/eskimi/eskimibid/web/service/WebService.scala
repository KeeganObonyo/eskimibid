package com.eskimi.eskimibid.web
package service

import akka.actor._
import akka.event.Logging
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.pattern.ask
import akka.util.Timeout

import com.eskimi.eskimibid._

import core.util.{ EskimiConfig, EskimiCoreServiceT }

trait EskimiPublicWebServiceT extends EskimiCoreServiceT {

  lazy val route = {
    path("bid") {
      complete {
        "OK"      
      }
    }
  } 
}
