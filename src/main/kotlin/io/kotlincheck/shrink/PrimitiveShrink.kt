package io.kotlincheck.shrink

import java.math.BigDecimal
import java.math.BigInteger

class DummyShrinker<T> : Shrinker<T> {
    override fun shrink(test: (T) -> Boolean, counterexample: T): T = counterexample
}

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

class IntShrinker(val origin: Int, val bound: Int) : Shrinker<Int> {
    private val longShrinker = LongShrinker(origin.toLong(), bound.toLong())

    override fun shrink(
            test: (Int) -> Boolean,
            counterexample: Int
    ): Int = longShrinker.shrink(
            { test(it.toInt()) },
            counterexample.toLong()
    ).toInt()
}

class ShortShrinker(val origin: Short, val bound: Short) : Shrinker<Short> {
    private val longShrinker = LongShrinker(origin.toLong(), bound.toLong())

    override fun shrink(
            test: (Short) -> Boolean,
            counterexample: Short
    ): Short = longShrinker.shrink(
            { test(it.toShort()) },
            counterexample.toLong()
    ).toShort()
}

class ByteShrinker(val origin: Byte, val bound: Byte) : Shrinker<Byte> {
    private val longShrinker = LongShrinker(origin.toLong(), bound.toLong())

    override fun shrink(
            test: (Byte) -> Boolean,
            counterexample: Byte
    ): Byte = longShrinker.shrink(
            { test(it.toByte()) },
            counterexample.toLong()
    ).toByte()
}

class CharShrinker(val origin: Char, val bound: Char) : Shrinker<Char> {
    private val longShrinker = LongShrinker(origin.toLong(), bound.toLong())

    override fun shrink(
            test: (Char) -> Boolean,
            counterexample: Char
    ): Char = longShrinker.shrink(
            { test(it.toChar()) },
            counterexample.toLong()
    ).toChar()
}

class BooleanShrinker : Shrinker<Boolean> {
    override fun shrink(
            test: (Boolean) -> Boolean,
            counterexample: Boolean
    ): Boolean = if (!counterexample) false else test(false)
}

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

class FloatShrinker(val origin: Float, val bound: Float) : Shrinker<Float> {
    private val doubleShrinker = DoubleShrinker(origin.toDouble(), bound.toDouble())

    override fun shrink(
            test: (Float) -> Boolean,
            counterexample: Float
    ): Float = doubleShrinker.shrink(
            { test(it.toFloat()) },
            counterexample.toDouble()
    ).toFloat()
}

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

class OneOfShrinker<T>(val values: List<T>) : Shrinker<T> {
    override fun shrink(
            test: (T) -> Boolean,
            counterexample: T
    ): T = values.find { !test(it) }!!
}
