package io.kotlincheck.shrink

class IntShrinker(val origin: Int, val bound: Int) : Shrinker<Int> {
    private val longShrinker = LongShrinker(origin.toLong(), bound.toLong())

    override fun shrink(
            test: (Int) -> Boolean,
            counterexample: Int
    ): Int = longShrinker.shrink(
            { test(it.toInt()) },
            counterexample.toLong()
    ).toInt()
}
