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
        val origin: BigDecimal,
        val bound: BigDecimal
) : Arbitrary<BigDecimal>(BigDecimalGen(origin, bound), BigDecimalShrinker(origin, bound))

class BigIntegerArbitrary(
        val origin: BigInteger,
        val bound: BigInteger
) : Arbitrary<BigInteger>(BigIntegerGen(origin, bound), BigIntegerShrinker(origin, bound))

class OneOfArbitrary<T>(values: List<T>) : Arbitrary<T>(OneOfGen(values), OneOfShrinker(values)) {

    constructor(values: Array<T>) : this(values.asList())

    constructor(values: Set<T>) : this(values.toList())
}
