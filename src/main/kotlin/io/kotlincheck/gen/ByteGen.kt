package io.kotlincheck.gen

import io.kotlincheck.Random
import io.kotlincheck.shrink.ByteShrinker
import io.kotlincheck.shrink.Shrinker

class ByteGen(
        val origin: Byte = Byte.MIN_VALUE,
        val bound: Byte = Byte.MAX_VALUE,
        shrinker: Shrinker<Byte>? = ByteShrinker(origin, bound)
) : Gen<Byte>(shrinker) {
    override fun generate(): Byte = Random.nextByte(origin, bound)
}
