package io.kotlincheck.arbitrary

import io.kotlincheck.PropSpec
import io.kotlincheck.forAll

class IntArbitrarySpec : PropSpec() {
    init {
        property("Return value in range origin..bound") {
            forAll(IntArbitrary(-1000, 0), IntArbitrary(1, 1000)) { origin, bound ->
                val arb = IntArbitrary(origin, bound)
                arb.generate() in origin..bound
            }
        }
    }
}
