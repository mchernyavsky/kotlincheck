package io.kotlincheck.arbitrary

import io.kotlincheck.PropSpec
import io.kotlincheck.forAll
import java.math.BigInteger

class BigIntegerArbitrarySpec : PropSpec() {
    init {
        property("Return value in range origin..bound") {
            forAll(
                BigIntegerArbitrary(BigInteger.valueOf(-1000), BigInteger.ZERO),
                BigIntegerArbitrary(BigInteger.ONE, BigInteger.valueOf(1000))
            ) { origin, bound ->
                val arb = BigIntegerArbitrary(origin, bound)
                arb.generate() in origin..bound
            }
        }
    }
}
