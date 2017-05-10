package io.kotlincheck.gen

import io.kotlincheck.PropSpec
import io.kotlincheck.Random
import io.kotlincheck.forAll
import io.kotlincheck.gen.containers.StringGen

class StringGenSpec : PropSpec() {
    init {
        property("Return string contains only printable chars") {
            forAll(StringGen()) { string ->
                string.chars().allMatch {
                    it in Random.PRINTABLE_ASCII_ORIGIN..Random.PRINTABLE_ASCII_BOUND
                }
            }
        }
    }
}
