package io.kotlincheck.gen

import io.kotlincheck.PropSpec
import io.kotlincheck.forAll
import java.math.BigInteger

class BigIntegerGenSpec : PropSpec() {
    init {
        property("Return value in range origin..bound") {
            forAll(
                    BigIntegerGen(BigInteger.valueOf(-1000), BigInteger.ZERO),
                    BigIntegerGen(BigInteger.ONE, BigInteger.valueOf(1000))
            ) { origin, bound ->
                val gen = BigIntegerGen(origin, bound)
                gen.generate() in origin..bound
            }
        }
    }
}
