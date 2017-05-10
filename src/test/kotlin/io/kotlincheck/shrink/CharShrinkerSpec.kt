package io.kotlincheck.shrink

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.ShouldSpec

class CharShrinkerSpec : ShouldSpec() {
    init {
        "CharShrinker.shrink"{
            should("return 0 if origin = 0 <= bound and 0 is counterexample") {
                val origin = 0.toChar()
                val bound = 100.toChar()
                val shrinker = CharShrinker(origin = origin, bound = bound)
                shrinker.shrink({ false }, bound) shouldBe 0.toChar()
                shrinker.shrink({ false }, origin) shouldBe 0.toChar()
            }

            should("return origin if origin > 0 and origin is counterexample") {
                val origin = 50.toChar()
                val bound = 100.toChar()
                val shrinker = CharShrinker(origin = origin, bound = bound)
                shrinker.shrink({ false }, bound) shouldBe origin
            }
        }
    }
}
