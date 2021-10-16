package io.kotlincheck

import java.math.BigDecimal
import java.math.BigInteger
import java.util.Random
import java.util.stream.DoubleStream
import java.util.stream.IntStream
import java.util.stream.LongStream

object Random {
    const val PRINTABLE_ASCII_ORIGIN: Int = 32
    const val PRINTABLE_ASCII_BOUND: Int = 127

    private val SOURCE: Random = Random()

    private const val BAD_RANGE: String = "bound must be greater than origin"

    fun nextBoolean(): Boolean = SOURCE.nextBoolean()

    fun nextByte(origin: Byte, bound: Byte): Byte = nextInt(origin.toInt(), bound.toInt()).toByte()
    fun nextChar(origin: Char, bound: Char): Char = nextInt(origin.code, bound.code).toChar()

    fun nextShort(origin: Short, bound: Short): Short = nextInt(origin.toInt(), bound.toInt()).toShort()
    fun nextInt(origin: Int, bound: Int): Int = ints(origin, bound).findAny().asInt
    fun nextLong(origin: Long, bound: Long): Long = longs(origin, bound).findAny().asLong
    fun nextBigInteger(origin: BigInteger, bound: BigInteger): BigInteger {
        if (origin >= bound) throw IllegalArgumentException(BAD_RANGE)
        val range = bound - origin
        while (true) {
            val value = BigInteger(range.bitLength(), SOURCE)
            if (value < range) {
                return value + origin
            }
        }
    }

    fun nextFloat(origin: Float, bound: Float): Float = nextDouble(origin.toDouble(), bound.toDouble()).toFloat()
    fun nextDouble(origin: Double, bound: Double): Double = doubles(origin, bound).findAny().asDouble
    fun nextGaussian(): Double = SOURCE.nextGaussian()
    fun nextBigDecimal(origin: BigDecimal, bound: BigDecimal): BigDecimal {
        if (origin >= bound) throw IllegalArgumentException(BAD_RANGE)
        val range = bound - origin
        val value = BigDecimal(nextDouble(0.0, 1.0))
        return value * range + origin
    }

    fun ints(origin: Int, bound: Int): IntStream = SOURCE.ints(origin, bound)
    fun longs(origin: Long, bound: Long): LongStream = SOURCE.longs(origin, bound)
    fun doubles(origin: Double, bound: Double): DoubleStream = SOURCE.doubles(origin, bound)
}
