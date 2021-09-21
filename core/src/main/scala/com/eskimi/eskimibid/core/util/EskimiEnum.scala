package com.eskimi.eskimibid.core
package util

object EskimiEnum {

  object EskimiEnvironment extends Enumeration {
    val Development = Value(1)
    val Staging     = Value(2)
    val Production  = Value(3)
  }

  object BidStatus extends Enumeration {
    val Accepted  = Value(200)
    val NoContent = Value(204)
  }
}
