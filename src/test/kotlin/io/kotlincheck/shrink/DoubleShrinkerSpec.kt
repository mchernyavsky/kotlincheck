package io.kotlincheck.shrink

import io.kotlintest.matchers.plusOrMinus
import io.kotlintest.matchers.should
import io.kotlintest.specs.ShouldSpec

class DoubleShrinkerSpec : ShouldSpec() {
    init {
        "DoubleShrinker.shrink"{
            val eps = 10e-9

            should("return 0 if origin <= 0 <= bound and 0 is counterexample") {
                val origin = (-100).toDouble()
                val bound = 100.toDouble()
                val shrinker = DoubleShrinker(origin = origin, bound = bound)
                shrinker.shrink({ false }, bound) should (0.0 plusOrMinus eps)
                shrinker.shrink({ false }, origin) should (0.0 plusOrMinus eps)
            }

            should("return origin if origin > 0 and origin is counterexample") {
                val origin = 50.toDouble()
                val bound = 100.toDouble()
                val shrinker = DoubleShrinker(origin = origin, bound = bound)
                shrinker.shrink({ false }, bound) should (origin plusOrMinus eps)
            }

            should("return bound if bound < 0 and bound is counterexample") {
                val origin = (-100).toDouble()
                val bound = (-50).toDouble()
                val shrinker = DoubleShrinker(origin = origin, bound = bound)
                shrinker.shrink({ false }, origin) should (bound plusOrMinus eps)
            }
        }
    }
}
