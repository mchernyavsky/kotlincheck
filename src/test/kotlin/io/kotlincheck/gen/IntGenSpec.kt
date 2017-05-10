package io.kotlincheck.gen

import io.kotlincheck.PropSpec
import io.kotlincheck.forAll

class IntGenSpec : PropSpec() {
    init {
        property("Return value in range origin..bound") {
            forAll(IntGen(-1000, 0), IntGen(1, 1000)) { origin, bound ->
                val gen = IntGen(origin, bound)
                gen.generate() in origin..bound
            }
        }
    }
}
