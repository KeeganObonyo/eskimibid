package com.eskimi.eskimibid.core
package util

import akka.actor.{ ActorRefFactory, Props }

import com.eskimi.eskimibid._

trait EskimiCoreServiceT {

  def actorRefFactory: ActorRefFactory
}
