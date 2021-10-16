package io.kotlincheck.shrink

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.ShouldSpec

class MutableListShrinkerSpec : ShouldSpec() {
    init {
        "MutableListShrinker<Int>.shrink" {
            should("return `emptyList<Int>` if `emptyList<Int>` is counterexample") {
                val shrinker = MutableListShrinker(IntShrinker(-100, 100), 5, false)
                val counterexample = mutableListOf(-1, 2, -3, 4, -5)
                shrinker.shrink({ false }, counterexample) shouldBe emptyList<Int>()
            }

            should("return a list of zeros of the same size if size fixed and the list is counterexample") {
                val shrinker = MutableListShrinker(IntShrinker(-100, 100), 5, true)
                val counterexample = mutableListOf(-1, 2, -3, 4, -5)
                shrinker.shrink({ false }, counterexample) shouldBe List(counterexample.size) { 0 }
            }
        }
    }
}
