package io.kotlincheck.gen

import io.kotlincheck.PropSpec
import io.kotlincheck.forAll

class CharGenSpec : PropSpec() {
    init {
        property("Return value in range origin..bound") {
            forAll(CharGen('A', 'Z'), CharGen('a', 'z')) { origin, bound ->
                val gen = CharGen(origin, bound)
                gen.generate() in origin..bound
            }
        }
    }
}
