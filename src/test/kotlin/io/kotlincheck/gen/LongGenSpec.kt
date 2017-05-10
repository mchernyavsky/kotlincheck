package io.kotlincheck.gen

import io.kotlincheck.PropSpec
import io.kotlincheck.forAll

class LongGenSpec : PropSpec() {
    init {
        property("Return value in range origin..bound") {
            forAll(LongGen(-1000, 0), LongGen(1, 1000)) { origin, bound ->
                val gen = LongGen(origin, bound)
                gen.generate() in origin..bound
            }
        }
    }
}
