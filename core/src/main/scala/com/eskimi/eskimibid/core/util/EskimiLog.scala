package com.eskimi.eskimibid.core
package util

import org.slf4j.LoggerFactory

trait EskimiLog {
  def log = LoggerFactory.getLogger(this.getClass)
}

trait EskimiCCPrinter {
  override def toString = EskimiUtil.getCaseClassString(this)
}
