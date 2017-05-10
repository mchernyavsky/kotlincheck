package io.kotlincheck.gen

import io.kotlincheck.Random
import io.kotlincheck.shrink.BooleanShrinker
import io.kotlincheck.shrink.Shrinker

class BooleanGen(shrinker: Shrinker<Boolean>? = BooleanShrinker()) : Gen<Boolean>(shrinker) {
    override fun generate(): Boolean = Random.nextBoolean()
}
