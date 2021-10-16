package io.kotlincheck.shrink

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.ShouldSpec
import java.math.BigDecimal

class BigDecimalShrinkerSpec : ShouldSpec() {
    init {
        "BigDecimalShrinker.shrink" {
            should("return 0 if origin <= 0 <= bound and 0 is counterexample") {
                val origin = BigDecimal.valueOf(-100)
                val bound = BigDecimal.valueOf(100)
                val shrinker = BigDecimalShrinker(origin = origin, bound = bound)
                shrinker.shrink({ false }, bound) shouldBe BigDecimal.ZERO
                shrinker.shrink({ false }, origin) shouldBe BigDecimal.ZERO
            }

            should("return origin if origin > 0 and origin is counterexample") {
                val origin = BigDecimal.valueOf(50)
                val bound = BigDecimal.valueOf(100)
                val shrinker = BigDecimalShrinker(origin = origin, bound = bound)
                shrinker.shrink({ false }, bound) shouldBe origin
            }

            should("return bound if bound < 0 and bound is counterexample") {
                val origin = BigDecimal.valueOf(-100)
                val bound = BigDecimal.valueOf(-50)
                val shrinker = BigDecimalShrinker(origin = origin, bound = bound)
                shrinker.shrink({ false }, origin) shouldBe bound
            }
        }
    }
}
