package io.kotlincheck.shrink

class DoubleShrinker(val origin: Double, val bound: Double) : Shrinker<Double> {
    override fun shrink(
            test: (Double) -> Boolean,
            counterexample: Double
    ): Double {
        var low = origin
        var high = bound
        var toHigh = false

        if (0.0 in low..high) {
            if (counterexample < 0.0) {
                high = 0.0
            } else {
                low = 0.0
            }
        }

        if (high <= 0.0) {
            low = counterexample
            toHigh = true
        } else {
            high = counterexample
        }

        val eps = 10e-9
        while (high - low > eps) {
            val mid = (low + high) / 2.0
            val result = test(mid)
            if (result && toHigh || !result && !toHigh) {
                high = mid
            } else {
                low = mid
            }
        }

        Math.round(high).toDouble().let {
            if (!test(it)) {
                return it
            }
        }

        return high
    }
}
