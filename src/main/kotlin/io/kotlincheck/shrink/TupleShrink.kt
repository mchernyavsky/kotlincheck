package io.kotlincheck.shrink

class PairShrinker<A, B>(
        val shrinker1: Shrinker<A>,
        val shrinker2: Shrinker<B>
) : Shrinker<Pair<A, B>> {
    override fun shrink(
            test: (Pair<A, B>) -> Boolean,
            counterexample: Pair<A, B>
    ): Pair<A, B> {
        var (first, second) = counterexample
        first = shrinker1.shrink({ test(Pair(it, second)) }, first)
        second = shrinker2.shrink({ test(Pair(first, it)) }, second)
        return Pair(first, second)
    }
}

class TripleShrinker<A, B, C>(
        val shrinker1: Shrinker<A>,
        val shrinker2: Shrinker<B>,
        val shrinker3: Shrinker<C>
) : Shrinker<Triple<A, B, C>> {
    override fun shrink(
            test: (Triple<A, B, C>) -> Boolean,
            counterexample: Triple<A, B, C>
    ): Triple<A, B, C> {
        var (first, second, third) = counterexample
        first = shrinker1.shrink({ test(Triple(it, second, third)) }, first)
        second = shrinker2.shrink({ test(Triple(first, it, third)) }, second)
        third = shrinker3.shrink({ test(Triple(first, second, it)) }, third)
        return Triple(first, second, third)
    }
}
