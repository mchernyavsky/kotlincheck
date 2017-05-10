package io.kotlincheck.shrink.containers

import io.kotlincheck.shrink.DoubleShrinker
import io.kotlincheck.shrink.IntShrinker
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.ShouldSpec

class PairShrinkerSpec : ShouldSpec() {
    init {
        "PairShrinker.shrink"{
            should("return Pair(0, 0.0) if Pair(0, 0.0) is counterexample") {
                val shrinker = PairShrinker(IntShrinker(-100, 100), DoubleShrinker(-100.0, 100.0))
                shrinker.shrink({ false }, Pair(50, 50.0)) shouldBe Pair(0, 0.0)
                shrinker.shrink({ false }, Pair(-50, -50.0)) shouldBe Pair(0, 0.0)
            }
        }
    }
}
