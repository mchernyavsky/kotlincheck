package io.kotlincheck.shrink

import java.math.BigDecimal

class BigDecimalShrinker(val origin: BigDecimal, val bound: BigDecimal) : Shrinker<BigDecimal> {
    override fun shrink(
            test: (BigDecimal) -> Boolean,
            counterexample: BigDecimal
    ): BigDecimal {
        var low = origin
        var high = bound
        var toHigh = false

        if (BigDecimal.ZERO in low..high) {
            if (counterexample < BigDecimal.ZERO) {
                high = BigDecimal.ZERO
            } else {
                low = BigDecimal.ZERO
            }
        }

        if (high <= BigDecimal.ZERO) {
            low = counterexample
            toHigh = true
        } else {
            high = counterexample
        }

        val TWO = BigDecimal.valueOf(2)
        while (low < high) {
            val mid = (low + high) / TWO
            val result = test(mid)
            if (result && toHigh || !result && !toHigh) {
                high = mid
            } else {
                low = mid
            }
        }

        // TODO: binary search
        generateSequence(high.setScale(0, BigDecimal.ROUND_HALF_UP)) {
            high.setScale(it.scale() + 1, BigDecimal.ROUND_HALF_UP)
        }.find {
            !test(it)
        }!!.let {
            return it
        }
    }
}
