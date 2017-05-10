package io.kotlincheck.gen

import io.kotlincheck.PropSpec
import io.kotlincheck.forAll

class ShortGenSpec : PropSpec() {
    init {
        property("Return value in range origin..bound") {
            forAll(ShortGen(-1000, 0), ShortGen(1, 1000)) { origin, bound ->
                val gen = ShortGen(origin, bound)
                gen.generate() in origin..bound
            }
        }
    }
}
