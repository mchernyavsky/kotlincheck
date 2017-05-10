package io.kotlincheck.shrink.containers

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.ShouldSpec

class StringShrinkerSpec : ShouldSpec() {
    init {
        "StringShrinker.shrink"{
            should("return empty string if empty string is counterexample") {
                val shrinker = StringShrinker(6, false)
                val counterexample = "abcdef"
                shrinker.shrink({ false }, counterexample) shouldBe ""
            }

            should("return a string of spaces of the same size if size fixed " +
                   "and the string is counterexample") {
                val shrinker = StringShrinker(6, true)
                val counterexample = "abcdef"
                shrinker.shrink({ false }, counterexample) shouldBe "      "
            }
        }
    }
}
