package io.kotlincheck.gen

import io.kotlincheck.PropSpec
import io.kotlincheck.forAll

class ByteGenSpec : PropSpec() {
    init {
        property("Return value in range origin..bound") {
            forAll(ByteGen(-100, 0), ByteGen(1, 100)) { origin, bound ->
                val gen = ByteGen(origin, bound)
                gen.generate() in origin..bound
            }
        }
    }
}
