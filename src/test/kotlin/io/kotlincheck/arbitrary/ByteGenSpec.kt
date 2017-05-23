package io.kotlincheck.arbitrary

import io.kotlincheck.PropSpec
import io.kotlincheck.forAll

class ByteArbitrarySpec : PropSpec() {
    init {
        property("Return value in range origin..bound") {
            forAll(ByteArbitrary(-100, 0), ByteArbitrary(1, 100)) { origin, bound ->
                val arb = ByteArbitrary(origin, bound)
                arb.generate() in origin..bound
            }
        }
    }
}
