package io.kotlincheck.arbitrary

import io.kotlincheck.PropSpec
import io.kotlincheck.forAll

class LongArbitrarySpec : PropSpec() {
    init {
        property("Return value in range origin..bound") {
            forAll(LongArbitrary(-1000, 0), LongArbitrary(1, 1000)) { origin, bound ->
                val arb = LongArbitrary(origin, bound)
                arb.generate() in origin..bound
            }
        }
    }
}
