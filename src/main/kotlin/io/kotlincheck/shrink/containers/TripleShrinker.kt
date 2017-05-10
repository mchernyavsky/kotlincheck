package io.kotlincheck.shrink.containers

import io.kotlincheck.shrink.Shrinker

class TripleShrinker<A, B, C>(
        val shrinker1: Shrinker<A>?,
        val shrinker2: Shrinker<B>?,
        val shrinker3: Shrinker<C>?
) : Shrinker<Triple<A, B, C>> {
    override fun shrink(
            test: (Triple<A, B, C>) -> Boolean,
            counterexample: Triple<A, B, C>
    ): Triple<A, B, C> {
        var shrinked = counterexample
        shrinker1?.let {
            val (first, second, third) = shrinked
            val firstShrinked = it.shrink({ test(Triple(it, second, third)) }, first)
            shrinked = Triple(firstShrinked, second, third)
        }
        shrinker2?.let {
            val (first, second, third) = shrinked
            val secondShrinked = it.shrink({ test(Triple(first, it, third)) }, second)
            shrinked = Triple(first, secondShrinked, third)
        }
        shrinker3?.let {
            val (first, second, third) = shrinked
            val thirdShrinked = it.shrink({ test(Triple(first, second, it)) }, third)
            shrinked = Triple(first, second, thirdShrinked)
        }
        return shrinked
    }
}
