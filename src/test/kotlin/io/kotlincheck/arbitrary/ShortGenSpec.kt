package io.kotlincheck.arbitrary

import io.kotlincheck.PropSpec
import io.kotlincheck.forAll

class ShortArbitrarySpec : PropSpec() {
    init {
        property("Return value in range origin..bound") {
            forAll(ShortArbitrary(-1000, 0), ShortArbitrary(1, 1000)) { origin, bound ->
                val arb = ShortArbitrary(origin, bound)
                arb.generate() in origin..bound
            }
        }
    }
}
