package io.kotlincheck.arbitrary

import io.kotlincheck.PropSpec
import io.kotlincheck.forAll

class CharArbitrarySpec : PropSpec() {
    init {
        property("Return value in range origin..bound") {
            forAll(CharArbitrary('A', 'Z'), CharArbitrary('a', 'z')) { origin, bound ->
                val arb = CharArbitrary(origin, bound)
                arb.generate() in origin..bound
            }
        }
    }
}
