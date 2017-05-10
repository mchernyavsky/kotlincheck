package io.kotlincheck.shrink

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.ShouldSpec
import java.math.BigInteger

class BigIntegerShrinkerSpec : ShouldSpec() {
    init {
        "BigIntegerShrinker.shrink"{
            should("return 0 if origin <= 0 <= bound and 0 is counterexample") {
                val origin = BigInteger.valueOf(-100)
                val bound = BigInteger.valueOf(100)
                val shrinker = BigIntegerShrinker(origin = origin, bound = bound)
                shrinker.shrink({ false }, bound) shouldBe BigInteger.ZERO
                shrinker.shrink({ false }, origin) shouldBe BigInteger.ZERO
            }

            should("return origin if origin > 0 and origin is counterexample") {
                val origin = BigInteger.valueOf(50)
                val bound = BigInteger.valueOf(100)
                val shrinker = BigIntegerShrinker(origin = origin, bound = bound)
                shrinker.shrink({ false }, bound) shouldBe origin
            }

            should("return bound if bound < 0 and bound is counterexample") {
                val origin = BigInteger.valueOf(-100)
                val bound = BigInteger.valueOf(-50)
                val shrinker = BigIntegerShrinker(origin = origin, bound = bound)
                shrinker.shrink({ false }, origin) shouldBe bound
            }
        }
    }
}
