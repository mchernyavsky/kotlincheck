package io.kotlincheck.arbitrary

import io.kotlincheck.PropSpec
import io.kotlincheck.forAll

class OneOfArbitrarySpec : PropSpec() {
    init {
        property("Return value in `values`") {
            forAll(MutableListArbitrary(IntArbitrary(-1000, 1000), 5, true)) { values ->
                val arb = OneOfArbitrary(values)
                arb.generate() in values
            }
        }
    }
}
