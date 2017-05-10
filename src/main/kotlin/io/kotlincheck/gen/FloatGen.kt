package io.kotlincheck.gen

import io.kotlincheck.Random
import io.kotlincheck.shrink.FloatShrinker
import io.kotlincheck.shrink.Shrinker

class FloatGen(
        val origin: Float = Float.MIN_VALUE,
        val bound: Float = Float.MAX_VALUE,
        shrinker: Shrinker<Float>? = FloatShrinker(origin, bound)
) : Gen<Float>(shrinker) {
    override fun generate(): Float = Random.nextFloat(origin, bound)
}
