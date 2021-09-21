package com.eskimi.eskimibid.core
package test

import akka.actor.{ ActorLogging, ActorSystem }

import akka.testkit.{ ImplicitSender, TestKit, TestProbe }

import org.scalatest.{ BeforeAndAfterAll, Matchers, WordSpecLike }

import com.eskimi.eskimibid._

import core.util._
import EskimiEnum._

abstract class EskimibidCoreTestServiceT extends TestKit(ActorSystem("MyTestSystem"))
    with EskimiCoreServiceT
    with ImplicitSender
    with WordSpecLike
    with Matchers
    with BeforeAndAfterAll
{
  override def actorRefFactory  = system

  override def beforeAll {
    Thread.sleep(2000)
  }

  override def afterAll {
    Thread.sleep(2000)
    TestKit.shutdownActorSystem(system)
  }

}
