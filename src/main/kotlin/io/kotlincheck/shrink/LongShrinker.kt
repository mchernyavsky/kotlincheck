package io.kotlincheck.shrink

class LongShrinker(val origin: Long, val bound: Long) : Shrinker<Long> {
    override fun shrink(
            test: (Long) -> Boolean,
            counterexample: Long
    ): Long {
        var low = origin
        var high = bound
        var toHigh = false

        if (0 in low..high) {
            if (counterexample < 0) {
                high = 0
            } else {
                low = 0
            }
        }

        if (high <= 0) {
            low = counterexample
            toHigh = true
        } else {
            high = counterexample
        }

        while (low < high) {
            val mid = (low + high) / 2
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
