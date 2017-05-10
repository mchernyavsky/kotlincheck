package io.kotlincheck.gen

import io.kotlincheck.Random
import io.kotlincheck.shrink.ShortShrinker
import io.kotlincheck.shrink.Shrinker

class ShortGen(
        val origin: Short = Short.MIN_VALUE,
        val bound: Short = Short.MAX_VALUE,
        shrinker: Shrinker<Short>? = ShortShrinker(origin, bound)
) : Gen<Short>(shrinker) {
    override fun generate(): Short = Random.nextShort(origin, bound)
}
