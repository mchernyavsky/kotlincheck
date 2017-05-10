package io.kotlincheck.shrink

import io.kotlintest.matchers.plusOrMinus
import io.kotlintest.matchers.should
import io.kotlintest.specs.ShouldSpec

class FloatShrinkerSpec : ShouldSpec() {
    init {
        "FloatShrinker.shrink"{
            val eps = 10e-9

            should("return 0 if origin <= 0 <= bound and 0 is counterexample") {
                val origin = -100f
                val bound = 100f
                val shrinker = FloatShrinker(origin = origin, bound = bound)
                shrinker.shrink({ false }, bound).toDouble() should (0.0 plusOrMinus eps)
                shrinker.shrink({ false }, origin).toDouble() should (0.0 plusOrMinus eps)
            }

            should("return origin if origin > 0 and origin is counterexample") {
                val origin = 50f
                val bound = 100f
                val shrinker = FloatShrinker(origin = origin, bound = bound)
                shrinker.shrink({ false }, bound).toDouble() should (origin.toDouble() plusOrMinus eps)
            }

            should("return bound if bound < 0 and bound is counterexample") {
                val origin = -100f
                val bound = -50f
                val shrinker = FloatShrinker(origin = origin, bound = bound)
                shrinker.shrink({ false }, origin).toDouble() should (bound.toDouble() plusOrMinus eps)
            }
        }
    }
}
