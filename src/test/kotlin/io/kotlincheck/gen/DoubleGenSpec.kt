package io.kotlincheck.gen

import io.kotlincheck.PropSpec
import io.kotlincheck.forAll

class DoubleGenSpec : PropSpec() {
    init {
        property("Return value in range origin..bound") {
            forAll(DoubleGen(-1000.0, 0.0), DoubleGen(1.0, 1000.0)) { origin, bound ->
                val gen = DoubleGen(origin, bound)
                gen.generate() in origin..bound
            }
        }
    }
}
