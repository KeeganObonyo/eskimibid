package com.eskimi.eskimibid.bid

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.util.{ Success, Failure }

import akka.actor.{ Actor, ActorLogging, ActorRef, Props }
import akka.pattern.ask
import akka.util.Timeout

import com.eskimi.eskimibid._

import core.util.{ EskimiCCPrinter, EskimiConfig, EskimiEnum }
import EskimiEnum.BidStatus

import campaigns._

object BidRequestService {

  case class Site(
  	id: String, 
  	domain: String
  ) extends EskimiCCPrinter

  case class Geo(
  	country: Option[String]
  ) extends EskimiCCPrinter

  case class User(
  	id: String, 
  	geo: Option[Geo]
  ) extends EskimiCCPrinter

  case class Device(
  	id: String, 
  	geo: Option[Geo]
  ) extends EskimiCCPrinter

  case class Impression(
  	id: String,
    wmin: Option[Int], 
    wmax: Option[Int], 
    w: Option[Int], 
    hmin: Option[Int], 
    hmax: Option[Int], 
    h: Option[Int], 
    bidFloor: Option[Double]
  ) extends EskimiCCPrinter

  case class BidRequest(
   id: String,
   imp: Option[List[Impression]],
   site: Site, 
   user: Option[User],
   device: Option[Device]
  ) extends EskimiCCPrinter


  case class Banner(
    id: Int,
  	height: Int,
  	width: Int,
  	src: String
  ) extends EskimiCCPrinter

  case class BidResponse(
  	id: String, 
  	bidRequestId: String, 
  	price: Double, 
  	adid: Option[String], 
  	banner: Option[Banner]
  ) extends EskimiCCPrinter
}


class BidRequestService extends Actor with ActorLogging {

     implicit val timeout = Timeout(EskimiConfig.httpServiceTimeout)

	import BidRequestService._
	def receive = {
		case req: BidRequest =>
		   log.info("processing " + req)
           val currentSender = sender
	}
}