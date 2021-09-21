package com.eskimi.eskimibid.core
package util

import org.scalatest.FlatSpec

class EskimiConfigSpec extends FlatSpec {

  object TestDevConfig extends EskimiConfigT {
    protected def getEnvironmentImpl = "dev"
  }
  
  object TestStagingConfig extends EskimiConfigT {
    protected def getEnvironmentImpl = "staging"
  }

  object TestProdConfig extends EskimiConfigT {
    protected def getEnvironmentImpl = "prod"
  }

  "The EskimiConfig" should "come up correctly" in {
    assert(TestDevConfig.getEnvironment.toString == "Development")
    assert(TestStagingConfig.getEnvironment.toString == "Staging")
    assert(TestProdConfig.getEnvironment.toString == "Production")
  }
}
