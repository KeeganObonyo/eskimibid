package com.eskimi.eskimibid.core
package util

import scala.concurrent.duration.{ Duration, FiniteDuration }

import org.joda.time.{ DateTime, DateTimeZone }
import org.joda.time.format.DateTimeFormat

object EskimiUtil {

  def getStackTrace(err: Throwable) = {
    val sw = new java.io.StringWriter
    err.printStackTrace(new java.io.PrintWriter(sw))
    sw.toString
  }

  def parseDecimal(s: String): Option[BigDecimal] = {
    try {
      Some(BigDecimal(s.toDouble))
    } catch {
      case _ : Throwable => None
    }
  }

  def parseInt(s: String): Option[Int] = {
    try {
      Some(s.toInt)
    } catch {
      case _ : Throwable => None
    }
  }

  def parseLong(s: String): Option[Long] = {
    try {
      Some(s.toLong)
    } catch {
      case _ : Throwable => None
    }
  }

  def parseFiniteDuration(str: String) : Option[FiniteDuration] = {
    try {
      Some(Duration(str)).collect { case d: FiniteDuration => d }
    } catch {
      case ex: NumberFormatException => None
    }
  }

  def getAnyString(any: Any, default: String = "") : String =
    any match {
      case Some(x) => x.toString
      case None    => default
      case x       => x.toString
    }

  private val DateTimeViewFormat  = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
  private val TimeViewFormat      = DateTimeFormat.forPattern("HH:mm:ss")

  def currentTime     = TimeViewFormat.print(DateTime.now(DateTimeZone.forID("Africa/Nairobi")))
  def currentDateTime = DateTimeViewFormat.print(DateTime.now(DateTimeZone.forID("Africa/Nairobi")))

  private[util] def getCaseClassString(cc: AnyRef) : String = {
    (cc.getClass.getDeclaredFields map (f  => {
      f.setAccessible(true)
      f.getName + "=" + getAnyString(f.get(cc), "None")
    })).mkString(cc.getClass.getSimpleName + "[", ";", "]")
  }

}
