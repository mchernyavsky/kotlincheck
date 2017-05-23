package io.kotlincheck.arbitrary

import io.kotlincheck.PropSpec
import io.kotlincheck.Random
import io.kotlincheck.forAll

class StringArbitrarySpec : PropSpec() {
    init {
        property("Return string contains only printable chars") {
            forAll(StringArbitrary(100, false)) { string ->
                string.chars().allMatch {
                    it in Random.PRINTABLE_ASCII_ORIGIN..Random.PRINTABLE_ASCII_BOUND
                }
            }
        }
    }
}
