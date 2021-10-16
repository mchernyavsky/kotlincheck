package io.kotlincheck.shrink

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.ShouldSpec

class LongShrinkerSpec : ShouldSpec() {
    init {
        "LongShrinker.shrink" {
            should("return 0 if origin <= 0 <= bound and 0 is counterexample") {
                val origin = (-100).toLong()
                val bound = 100.toLong()
                val shrinker = LongShrinker(origin = origin, bound = bound)
                shrinker.shrink({ false }, bound) shouldBe 0.toLong()
                shrinker.shrink({ false }, origin) shouldBe 0.toLong()
            }

            should("return origin if origin > 0 and origin is counterexample") {
                val origin = 50.toLong()
                val bound = 100.toLong()
                val shrinker = LongShrinker(origin = origin, bound = bound)
                shrinker.shrink({ false }, bound) shouldBe origin
            }

            should("return bound if bound < 0 and bound is counterexample") {
                val origin = (-100).toLong()
                val bound = (-50).toLong()
                val shrinker = LongShrinker(origin = origin, bound = bound)
                shrinker.shrink({ false }, origin) shouldBe bound
            }
        }
    }
}
