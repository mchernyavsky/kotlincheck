package io.kotlincheck.shrink.containers

import io.kotlincheck.Random
import io.kotlincheck.shrink.CharShrinker
import io.kotlincheck.shrink.Shrinker

class StringShrinker(
        val sizeBound: Int,
        val fixedSize: Boolean
) : Shrinker<String> {
    private val charListShrinker = ListShrinker(
            CharShrinker(
                    Random.PRINTABLE_ASCII_ORIGIN.toChar(),
                    Random.PRINTABLE_ASCII_BOUND.toChar()
            ),
            sizeBound,
            fixedSize
    )

    override fun shrink(
            test: (String) -> Boolean,
            counterexample: String
    ): String = charListShrinker.shrink(
            { test(it.joinToString("")) },
            counterexample.toList()
    ).joinToString("")
}
