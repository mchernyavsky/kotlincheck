package io.kotlincheck.gen

import io.kotlincheck.PropSpec
import io.kotlincheck.forAll

class FloatGenSpec : PropSpec() {
    init {
        property("Return value in range origin..bound") {
            forAll(FloatGen(-1000f, 0f), FloatGen(1f, 1000f)) { origin, bound ->
                val gen = FloatGen(origin, bound)
                gen.generate() in origin..bound
            }
        }
    }
}
