package io.kotlincheck.gen

import io.kotlincheck.shrink.Shrinker
import io.kotlincheck.shrink.containers.PairShrinker

class PairGen<A, B>(
        val gen1: Gen<A>,
        val gen2: Gen<B>,
        shrinker: Shrinker<Pair<A, B>>? = PairShrinker(gen1.shrinker, gen2.shrinker)
) : Gen<Pair<A, B>>(shrinker) {
    override fun generate(): Pair<A, B> = Pair(gen1.generate(), gen2.generate())
}
