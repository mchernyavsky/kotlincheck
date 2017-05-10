package io.kotlincheck.shrink.containers

import io.kotlincheck.shrink.Shrinker

class PairShrinker<A, B>(
        val shrinker1: Shrinker<A>?,
        val shrinker2: Shrinker<B>?
) : Shrinker<Pair<A, B>> {
    override fun shrink(
            test: (Pair<A, B>) -> Boolean,
            counterexample: Pair<A, B>
    ): Pair<A, B> {
        var shrinked = counterexample
        shrinker1?.let {
            val (first, second) = shrinked
            val firstShrinked = it.shrink({ test(Pair(it, second)) }, first)
            shrinked = Pair(firstShrinked, second)
        }
        shrinker2?.let {
            val (first, second) = shrinked
            val secondShrinked = it.shrink({ test(Pair(first, it)) }, second)
            shrinked = Pair(first, secondShrinked)
        }
        return shrinked
    }
}
