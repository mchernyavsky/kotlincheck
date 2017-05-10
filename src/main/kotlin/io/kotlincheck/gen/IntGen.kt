package io.kotlincheck.gen

import io.kotlincheck.Random
import io.kotlincheck.shrink.IntShrinker
import io.kotlincheck.shrink.Shrinker

class IntGen(
        val origin: Int = Int.MIN_VALUE,
        val bound: Int = Int.MAX_VALUE,
        shrinker: Shrinker<Int>? = IntShrinker(origin, bound)
) : Gen<Int>(shrinker) {
    override fun generate(): Int = Random.nextInt(origin, bound)
}
