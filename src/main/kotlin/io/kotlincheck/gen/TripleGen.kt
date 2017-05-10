package io.kotlincheck.gen

import io.kotlincheck.shrink.Shrinker
import io.kotlincheck.shrink.containers.TripleShrinker

class TripleGen<A, B, C>(
        val gen1: Gen<A>,
        val gen2: Gen<B>,
        val gen3: Gen<C>,
        shrinker: Shrinker<Triple<A, B, C>>? = TripleShrinker(gen1.shrinker, gen2.shrinker, gen3.shrinker)
) : Gen<Triple<A, B, C>>(shrinker) {
    override fun generate(): Triple<A, B, C> = Triple(gen1.generate(), gen2.generate(), gen3.generate())
}
