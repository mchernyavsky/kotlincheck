package io.kotlincheck.gen

import io.kotlincheck.Random
import io.kotlincheck.shrink.LongShrinker
import io.kotlincheck.shrink.Shrinker

class LongGen(
        val origin: Long = Long.MIN_VALUE,
        val bound: Long = Long.MAX_VALUE,
        shrinker: Shrinker<Long>? = LongShrinker(origin, bound)
) : Gen<Long>(shrinker) {
    override fun generate(): Long = Random.nextLong(origin, bound)
}
