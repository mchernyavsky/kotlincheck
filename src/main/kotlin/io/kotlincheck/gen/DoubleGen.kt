package io.kotlincheck.gen

import io.kotlincheck.Random
import io.kotlincheck.shrink.DoubleShrinker
import io.kotlincheck.shrink.Shrinker

class DoubleGen(
        val origin: Double = Double.MIN_VALUE,
        val bound: Double = Double.MAX_VALUE,
        shrinker: Shrinker<Double>? = DoubleShrinker(origin, bound)
) : Gen<Double>(shrinker) {
    override fun generate(): Double = Random.nextDouble(origin, bound)
}
