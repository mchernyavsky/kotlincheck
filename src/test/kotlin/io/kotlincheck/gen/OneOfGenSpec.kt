package io.kotlincheck.gen

import io.kotlincheck.PropSpec
import io.kotlincheck.forAll
import io.kotlincheck.gen.containers.ListGen

class OneOfGenSpec : PropSpec() {
    init {
        property("Return value in `values`") {
            forAll(ListGen(IntGen(), 5, true)) { values ->
                val gen = OneOfGen(values)
                gen.generate() in values
            }
        }
    }
}
