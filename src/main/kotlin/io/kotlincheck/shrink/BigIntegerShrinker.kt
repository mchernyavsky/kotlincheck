package io.kotlincheck.shrink

import java.math.BigInteger

class BigIntegerShrinker(val origin: BigInteger, val bound: BigInteger) : Shrinker<BigInteger> {
    override fun shrink(
            test: (BigInteger) -> Boolean,
            counterexample: BigInteger
    ): BigInteger {
        var low = origin
        var high = bound
        var toHigh = false

        if (BigInteger.ZERO in low..high) {
            if (counterexample < BigInteger.ZERO) {
                high = BigInteger.ZERO
            } else {
                low = BigInteger.ZERO
            }
        }

        if (high <= BigInteger.ZERO) {
            low = counterexample
            toHigh = true
        } else {
            high = counterexample
        }

        val TWO = BigInteger.valueOf(2)
        while (low < high) {
            val mid = (low + high) / TWO
            val result = test(mid)
            if (result && toHigh || !result && !toHigh) {
                high = mid
            } else {
                low = mid
            }
        }

        return high
    }
}
