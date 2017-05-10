package io.kotlincheck.gen

import io.kotlincheck.PropSpec
import io.kotlincheck.forAll
import java.math.BigDecimal

class BigDecimalGenSpec : PropSpec() {
    init {
        property("Return value in range origin..bound") {
            forAll(
                    BigDecimalGen(BigDecimal.valueOf(-1000), BigDecimal.ZERO),
                    BigDecimalGen(BigDecimal.ONE, BigDecimal.valueOf(1000))
            ) { origin, bound ->
                val gen = BigDecimalGen(origin, bound)
                gen.generate() in origin..bound
            }
        }
    }
}
