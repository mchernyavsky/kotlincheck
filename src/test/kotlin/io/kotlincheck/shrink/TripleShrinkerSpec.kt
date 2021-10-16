package io.kotlincheck.shrink

import io.kotlintest.matchers.shouldBe

class TripleShrinkerSpec : io.kotlintest.specs.ShouldSpec() {
    init {
        "TripleShrinker.shrink" {
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
