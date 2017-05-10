package io.kotlincheck.shrink

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.ShouldSpec

class ByteShrinkerSpec : ShouldSpec() {
    init {
        "ByteShrinker.shrink"{
            should("return 0 if origin <= 0 <= bound and 0 is counterexample") {
                val origin = (-100).toByte()
                val bound = 100.toByte()
                val shrinker = ByteShrinker(origin = origin, bound = bound)
                shrinker.shrink({ false }, bound) shouldBe 0.toByte()
                shrinker.shrink({ false }, origin) shouldBe 0.toByte()
            }

            should("return origin if origin > 0 and origin is counterexample") {
                val origin = 50.toByte()
                val bound = 100.toByte()
                val shrinker = ByteShrinker(origin = origin, bound = bound)
                shrinker.shrink({ false }, bound) shouldBe origin
            }

            should("return bound if bound < 0 and bound is counterexample") {
                val origin = (-100).toByte()
                val bound = (-50).toByte()
                val shrinker = ByteShrinker(origin = origin, bound = bound)
                shrinker.shrink({ false }, origin) shouldBe bound
            }
        }
    }
}
