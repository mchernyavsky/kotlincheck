package io.kotlincheck.shrink

class ShortShrinker(val origin: Short, val bound: Short) : Shrinker<Short> {
    private val longShrinker = LongShrinker(origin.toLong(), bound.toLong())

    override fun shrink(
            test: (Short) -> Boolean,
            counterexample: Short
    ): Short = longShrinker.shrink(
            { test(it.toShort()) },
            counterexample.toLong()
    ).toShort()
}
