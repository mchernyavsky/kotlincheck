package io.kotlincheck.arbitrary

import io.kotlincheck.PropSpec
import io.kotlincheck.forAll
import java.math.BigDecimal

class BigDecimalArbitrarySpec : PropSpec() {
    init {
        property("Return value in range origin..bound") {
            forAll(
                    BigDecimalArbitrary(java.math.BigDecimal.valueOf(-1000), BigDecimal.ZERO),
                    BigDecimalArbitrary(java.math.BigDecimal.ONE, BigDecimal.valueOf(1000))
            ) { origin, bound ->
                val arb = BigDecimalArbitrary(origin, bound)
                arb.generate() in origin..bound
            }
        }
    }
}
