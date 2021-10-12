package com.eskimi.eskimibid.campaigns

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.language.postfixOps

import akka.actor.Props
import akka.pattern.ask

import akka.actor.{ ActorLogging, ActorSystem }
import akka.testkit.{ ImplicitSender, TestKit, TestProbe }

import org.scalatest.{ BeforeAndAfterAll, Matchers, WordSpecLike }

import com.eskimi.eskimibid.campaigns._

import CampaignsService._

class CampaignsServiceSpec extends TestKit(ActorSystem("MyTestSystem"))
    with ImplicitSender
    with WordSpecLike
    with Matchers
    with BeforeAndAfterAll {

  val transactionRegistryProbe = TestProbe()
  val campaignService          = system.actorOf(Props(new CampaignsService))

  "The CampaignsService" must {

    "return a list of active bids" in {
      campaignService ! ActiveCampaignserviceRequest()
      expectMsg(ActiveCampaignserviceResponce(
        activeCampaigns = List(Campaign1,Campaign2),
      ))

      transactionRegistryProbe.expectNoMsg(100 millis)
    }
  }
}
