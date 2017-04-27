package io.kotlincheck

import java.math.BigDecimal
import java.math.BigInteger
import java.util.Random
import java.util.stream.DoubleStream
import java.util.stream.IntStream
import java.util.stream.LongStream

internal object Random {
    private val SOURCE: Random = Random()

    private val PRINTABLE_ASCII_ORIGIN = 32
    private val PRINTABLE_ASCII_BOUND = 127

    private val BAD_RANGE = "bound must be greater than origin"

    fun nextBoolean(): Boolean = SOURCE.nextBoolean()

    fun nextByte(origin: Byte, bound: Byte): Byte = nextInt(origin.toInt(), bound.toInt()).toByte()
    fun nextChar(origin: Char, bound: Char): Char = nextInt(origin.toInt(), bound.toInt()).toChar()
    fun nextPrintableChar(): Char = nextInt(PRINTABLE_ASCII_ORIGIN, PRINTABLE_ASCII_BOUND).toChar()

    fun nextShort(origin: Short, bound: Short): Short = nextInt(origin.toInt(), bound.toInt()).toShort()
    fun nextInt(origin: Int, bound: Int): Int = ints(origin, bound).findAny().asInt
    fun nextLong(origin: Long, bound: Long): Long = longs(origin, bound).findAny().asLong
    fun nextBigInteger(origin: BigInteger, bound: BigInteger): BigInteger {
        if (origin >= bound) {
            throw IllegalArgumentException(BAD_RANGE)
        }

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
        if (origin >= bound) {
            throw IllegalArgumentException(BAD_RANGE)
        }

        val range = bound - origin
        val value = BigDecimal(nextGaussian())
        return value * range + origin
    }

    fun ints(origin: Int, bound: Int): IntStream = SOURCE.ints(origin, bound)
    fun longs(origin: Long, bound: Long): LongStream = SOURCE.longs(origin, bound)
    fun doubles(origin: Double, bound: Double): DoubleStream = SOURCE.doubles(origin, bound)
}
