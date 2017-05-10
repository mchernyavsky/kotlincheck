package io.kotlincheck.shrink

class CharShrinker(val origin: Char, val bound: Char) : Shrinker<Char> {
    private val longShrinker = LongShrinker(origin.toLong(), bound.toLong())

    override fun shrink(
            test: (Char) -> Boolean,
            counterexample: Char
    ): Char = longShrinker.shrink(
            { test(it.toChar()) },
            counterexample.toLong()
    ).toChar()
}
