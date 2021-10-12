package com.eskimi.eskimibid.campaigns

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.util.{ Success, Failure }

import akka.actor.{ Actor, ActorLogging, ActorRef, Props }
import akka.pattern.ask
import akka.util.Timeout

import com.eskimi.eskimibid._

import core.util.{ EskimiCCPrinter, EskimiConfig }


object CampaignsService {

	sealed trait Targeting { val targetedSiteIds: List[String] }
	sealed trait Banner { val id: Int; val src: String; val width: Int; val height: Int }
	sealed trait Campaign { val id: Int; val country: String; val targeting: Targeting; val banners: List[Banner]; val bid: Double }

	case object Targeting extends Targeting { val targetedSiteIds = List("0006a522ce0f4bbbbaa6b3c38cafaa0f", "0006a522cf4bbbbaa893b3c38cafaa0f", "0006a967ce0f4bbbbaa6b3c38cafaaq3") }
	case object Banner1 extends Banner { val id = 1; val src = "https://business.eskimi.com/wp-content/uploads/2020/06/openGraph.jpeg"; val width = 400; val height = 200 }
	case object Banner2 extends Banner { val id = 2; val src = "https://business.eskimi.com/wp-content/uploads/2020/07/openGraph.jpeg"; val width = 500; val height = 250 }
	case object Banner3 extends Banner { val id = 3; val src = "https://business.eskimi.com/wp-content/uploads/2020/08/openGraph.jpeg"; val width = 600; val height = 300 }

	case object Campaign1 extends Campaign { val id = 1; val country = "KE"; val targeting = Targeting; val banners = List(Banner1,Banner2,Banner3); val bid = 44.0 }
	case object Campaign2 extends Campaign { val id = 2; val country = "SA"; val targeting = Targeting; val banners = List(Banner1,Banner2,Banner3); val bid = 44.0 }
	
    case class ActiveCampaignserviceRequest(
    ) extends EskimiCCPrinter

    case class ActiveCampaignserviceResponce(
      activeCampaigns: List[Campaign]
    )

}

class CampaignsService extends Actor with ActorLogging {

    implicit val timeout = Timeout(EskimiConfig.httpServiceTimeout)

    import CampaignsService._
    def receive = {
    	case req: ActiveCampaignserviceRequest =>
		   log.info("processing " + req)
           val currentSender = sender

           currentSender ! ActiveCampaignserviceResponce(
            activeCampaigns = List(Campaign1, Campaign2)
          )
    }
}