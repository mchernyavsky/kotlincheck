package io.kotlincheck.gen

import io.kotlincheck.Random
import io.kotlincheck.shrink.BigDecimalShrinker
import io.kotlincheck.shrink.Shrinker
import java.math.BigDecimal

class BigDecimalGen(
        val origin: BigDecimal,
        val bound: BigDecimal,
        shrinker: Shrinker<BigDecimal>? = BigDecimalShrinker(origin, bound)
) : Gen<BigDecimal>(shrinker) {
    override fun generate(): BigDecimal = Random.nextBigDecimal(origin, bound)
}
