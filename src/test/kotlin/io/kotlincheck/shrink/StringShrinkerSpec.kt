package io.kotlincheck.shrink

import io.kotlintest.matchers.shouldBe

class StringShrinkerSpec : io.kotlintest.specs.ShouldSpec() {
    init {
        "StringShrinker.shrink"{
            should("return empty string if empty string is counterexample") {
                val shrinker = io.kotlincheck.shrink.StringShrinker(6, false)
                val counterexample = "abcdef"
                shrinker.shrink({ false }, counterexample) shouldBe ""
            }

            should("return a string of spaces of the same size if size fixed " +
                   "and the string is counterexample") {
                val shrinker = io.kotlincheck.shrink.StringShrinker(6, true)
                val counterexample = "abcdef"
                shrinker.shrink({ false }, counterexample) shouldBe "      "
            }
        }
    }
}
