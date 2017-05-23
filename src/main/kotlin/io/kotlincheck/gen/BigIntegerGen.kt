package io.kotlincheck.gen

import io.kotlincheck.Random
import io.kotlincheck.shrink.BigIntegerShrinker
import io.kotlincheck.shrink.Shrinker
import java.math.BigInteger

class BigIntegerGen(
        val origin: BigInteger,
        val bound: BigInteger,
        shrinker: Shrinker<BigInteger>? = BigIntegerShrinker(origin, bound)
) : Gen<BigInteger>(shrinker) {
    override fun generate(): BigInteger = Random.nextBigInteger(origin, bound)
}
