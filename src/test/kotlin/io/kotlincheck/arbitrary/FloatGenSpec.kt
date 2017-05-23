package io.kotlincheck.arbitrary

import io.kotlincheck.PropSpec
import io.kotlincheck.forAll

class FloatArbitrarySpec : PropSpec() {
    init {
        property("Return value in range origin..bound") {
            forAll(FloatArbitrary(-1000f, 0f), FloatArbitrary(1f, 1000f)) { origin, bound ->
                val arb = FloatArbitrary(origin, bound)
                arb.generate() in origin..bound
            }
        }
    }
}
