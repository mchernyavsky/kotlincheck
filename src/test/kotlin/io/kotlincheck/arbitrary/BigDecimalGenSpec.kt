package io.kotlincheck.arbitrary

import io.kotlincheck.PropSpec
import io.kotlincheck.forAll
import java.math.BigDecimal

class BigDecimalArbitrarySpec : PropSpec() {
    init {
        property("Return value in range origin..bound") {
            forAll(
                BigDecimalArbitrary(BigDecimal.valueOf(-1000), BigDecimal.ZERO),
                BigDecimalArbitrary(BigDecimal.ONE, BigDecimal.valueOf(1000))
            ) { origin, bound ->
                val arb = BigDecimalArbitrary(origin, bound)
                arb.generate() in origin..bound
            }
        }
    }
}
