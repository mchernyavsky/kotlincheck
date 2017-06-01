package io.kotlincheck.arbitrary

import io.kotlincheck.Random
import io.kotlincheck.gen.*
import io.kotlincheck.shrink.*
import java.math.BigDecimal
import java.math.BigInteger

class LongArbitrary(
        val origin: Long = Long.MIN_VALUE,
        val bound: Long = Long.MAX_VALUE
) : Arbitrary<Long>(LongGen(origin, bound), LongShrinker(origin, bound))

class IntArbitrary(
        val origin: Int = Int.MIN_VALUE,
        val bound: Int = Int.MAX_VALUE
) : Arbitrary<Int>(IntGen(origin, bound), IntShrinker(origin, bound))

class ShortArbitrary(
        val origin: Short = Short.MIN_VALUE,
        val bound: Short = Short.MAX_VALUE
) : Arbitrary<Short>(ShortGen(origin, bound), ShortShrinker(origin, bound))

class ByteArbitrary(
        val origin: Byte = Byte.MIN_VALUE,
        val bound: Byte = Byte.MAX_VALUE
) : Arbitrary<Byte>(ByteGen(origin, bound), ByteShrinker(origin, bound))

class CharArbitrary(
        val origin: Char = Random.PRINTABLE_ASCII_ORIGIN.toChar(),
        val bound: Char = Random.PRINTABLE_ASCII_BOUND.toChar()
) : Arbitrary<Char>(CharGen(origin, bound), CharShrinker(origin, bound))

class BooleanArbitrary : Arbitrary<Boolean>(BooleanGen(), BooleanShrinker())

class DoubleArbitrary(
        val origin: Double = Double.MIN_VALUE,
        val bound: Double = Double.MAX_VALUE
) : Arbitrary<Double>(DoubleGen(origin, bound), DoubleShrinker(origin, bound))

class FloatArbitrary(
        val origin: Float = Float.MIN_VALUE,
        val bound: Float = Float.MAX_VALUE
) : Arbitrary<Float>(FloatGen(origin, bound), FloatShrinker(origin, bound))

class BigDecimalArbitrary(
        val origin: BigDecimal = BigDecimal.valueOf(Double.MIN_VALUE),
        val bound: BigDecimal = BigDecimal.valueOf(Double.MAX_VALUE)
) : Arbitrary<BigDecimal>(BigDecimalGen(origin, bound), BigDecimalShrinker(origin, bound))

class BigIntegerArbitrary(
        val origin: BigInteger = BigInteger.valueOf(Long.MIN_VALUE),
        val bound: BigInteger = BigInteger.valueOf(Long.MAX_VALUE)
) : Arbitrary<BigInteger>(BigIntegerGen(origin, bound), BigIntegerShrinker(origin, bound))

class OneOfArbitrary<T>(values: List<T>) : Arbitrary<T>(OneOfGen(values), OneOfShrinker(values))

class OneOfGenArbitrary<T>(gens: List<Gen<T>>) : Arbitrary<T>(OneOfGenGen(gens))

/** API */

fun longs(
        origin: Long = Long.MIN_VALUE,
        bound: Long = Long.MAX_VALUE
): LongArbitrary = LongArbitrary(origin = origin, bound = bound)

fun ints(
        origin: Int = Int.MIN_VALUE,
        bound: Int = Int.MAX_VALUE
): IntArbitrary = IntArbitrary(origin = origin, bound = bound)

fun shorts(
        origin: Short = Short.MIN_VALUE,
        bound: Short = Short.MAX_VALUE
): ShortArbitrary = ShortArbitrary(origin = origin, bound = bound)

fun bytes(
        origin: Byte = Byte.MIN_VALUE,
        bound: Byte = Byte.MAX_VALUE
): ByteArbitrary = ByteArbitrary(origin = origin, bound = bound)

fun chars(
        origin: Char = Random.PRINTABLE_ASCII_ORIGIN.toChar(),
        bound: Char = Random.PRINTABLE_ASCII_BOUND.toChar()
): CharArbitrary = CharArbitrary(origin = origin, bound = bound)

fun booleans(): BooleanArbitrary = BooleanArbitrary()

fun doubles(
        origin: Double = Double.MIN_VALUE,
        bound: Double = Double.MAX_VALUE
): DoubleArbitrary = DoubleArbitrary(origin = origin, bound = bound)

fun floats(
        origin: Float = Float.MIN_VALUE,
        bound: Float = Float.MAX_VALUE
): FloatArbitrary = FloatArbitrary(origin = origin, bound = bound)

fun bigDecimals(
        origin: BigDecimal = BigDecimal.valueOf(Double.MIN_VALUE),
        bound: BigDecimal = BigDecimal.valueOf(Double.MAX_VALUE)
): BigDecimalArbitrary = BigDecimalArbitrary(origin = origin, bound = bound)

fun bigInts(
        origin: BigInteger = BigInteger.valueOf(Long.MIN_VALUE),
        bound: BigInteger = BigInteger.valueOf(Long.MAX_VALUE)
): BigIntegerArbitrary = BigIntegerArbitrary(origin = origin, bound = bound)

fun <T> const(constant: T): Arbitrary<T> = Arbitrary(object : Gen<T> {
    override fun generate(): T = constant

    override fun isAcceptable(value: T): Boolean = value == constant
})

fun <T> oneOf(values: List<T>): OneOfArbitrary<T> = OneOfArbitrary<T>(values)

fun <T> oneOf(vararg values: T): OneOfArbitrary<T> = OneOfArbitrary<T>(values.asList())

fun <T> oneOf(values: Set<T>): OneOfArbitrary<T> = OneOfArbitrary<T>(values.toList())

fun <T> oneOf(gens: List<Gen<T>>): OneOfGenArbitrary<T> = OneOfGenArbitrary<T>(gens)

fun <T> oneOf(vararg gens: Gen<T>): OneOfGenArbitrary<T> = OneOfGenArbitrary<T>(gens.asList())

fun <T> oneOf(gens: Set<Gen<T>>): OneOfGenArbitrary<T> = OneOfGenArbitrary<T>(gens.toList())
