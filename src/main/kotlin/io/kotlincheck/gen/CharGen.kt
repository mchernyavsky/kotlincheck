package io.kotlincheck.gen

import io.kotlincheck.Random
import io.kotlincheck.shrink.CharShrinker
import io.kotlincheck.shrink.Shrinker

class CharGen(
        val origin: Char = Random.PRINTABLE_ASCII_ORIGIN.toChar(),
        val bound: Char = Random.PRINTABLE_ASCII_BOUND.toChar(),
        shrinker: Shrinker<Char>? = CharShrinker(origin, bound)
) : Gen<Char>(shrinker) {
    override fun generate(): Char = Random.nextChar(origin, bound)
}
