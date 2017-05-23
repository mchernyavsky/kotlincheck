package io.kotlincheck.arbitrary

import io.kotlincheck.PropSpec
import io.kotlincheck.forAll

class DoubleArbitrarySpec : PropSpec() {
    init {
        property("Return value in range origin..bound") {
            forAll(DoubleArbitrary(-1000.0, 0.0), DoubleArbitrary(1.0, 1000.0)) { origin, bound ->
                val arb = DoubleArbitrary(origin, bound)
                arb.generate() in origin..bound
            }
        }
    }
}
