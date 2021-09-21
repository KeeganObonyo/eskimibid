package com.eskimi.eskimibid.core
package util

import collection.JavaConverters._
import com.typesafe.config.{ ConfigFactory, ConfigValue }

import com.eskimi.eskimibid._

import EskimiEnum.EskimiEnvironment

object EskimiConfig extends EskimiConfigT {
  protected def getEnvironmentImpl = System.getenv("ESKIMIBID_ENV")
}

private[util] trait EskimiConfigT {

  val config = ConfigFactory.load
  config.checkValid(ConfigFactory.defaultReference)

  protected def getEnvironmentImpl: String
  protected lazy val environment = getEnvironmentImpl

  def getEnvironment : EskimiEnvironment.Value = environment match {
    case "dev"     => EskimiEnvironment.Development
    case "staging" => EskimiEnvironment.Staging
    case "prod"    => EskimiEnvironment.Production
    case x         => throw new Exception("Unexpected environment value: " + x)
  }

  // Timeouts
  val httpServiceTimeout   = EskimiUtil.parseFiniteDuration(config.getString("eskimibid.timeout.http-service")).get

  // Web
  val publiclWebInterface = config.getString("eskimibid.web.public.interface")
  val publiclHttpPort     = config.getInt("eskimibid.web.public.http-port")
}
