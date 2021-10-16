package io.kotlincheck.shrink

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.ShouldSpec

class BooleanShrinkerSpec : ShouldSpec() {
    init {
        "BooleanShrinker.shrink" {
            should("return false if false is counterexample") {
                val shrinker = BooleanShrinker()
                shrinker.shrink({ false }, true) shouldBe false
            }
        }
    }
}
