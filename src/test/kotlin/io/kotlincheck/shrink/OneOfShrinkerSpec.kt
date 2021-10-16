package io.kotlincheck.shrink

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.ShouldSpec

class OneOfShrinkerSpec : ShouldSpec() {
    init {
        "OneOfShrinker.shrink" {
            should("return first element of `values` that does not satisfy the property") {
                val shrinker = OneOfShrinker(listOf(1, 2))
                shrinker.shrink({ it < 1 }, 2) shouldBe 1
                shrinker.shrink({ it < 2 }, 2) shouldBe 2
            }
        }
    }
}
