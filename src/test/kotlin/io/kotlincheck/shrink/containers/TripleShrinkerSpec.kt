package io.kotlincheck.shrink.containers

import io.kotlincheck.shrink.BooleanShrinker
import io.kotlincheck.shrink.DoubleShrinker
import io.kotlincheck.shrink.IntShrinker
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.ShouldSpec

class TripleShrinkerSpec : ShouldSpec() {
    init {
        "TripleShrinker.shrink"{
            should("return Triple(0, 0.0, false) if Triple(0, 0.0, false) is counterexample") {
                val shrinker = TripleShrinker(
                        IntShrinker(-100, 100),
                        DoubleShrinker(-100.0, 100.0),
                        BooleanShrinker()
                )
                shrinker.shrink({ false }, Triple(50, 50.0, true)) shouldBe Triple(0, 0.0, false)
                shrinker.shrink({ false }, Triple(-50, -50.0, false)) shouldBe Triple(0, 0.0, false)
            }
        }
    }
}
