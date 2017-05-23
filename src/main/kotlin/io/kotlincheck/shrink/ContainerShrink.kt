package io.kotlincheck.shrink

import io.kotlincheck.Random

abstract class ContainerShrinker<T>(val sizeBound: Int, val fixedSize: Boolean) : Shrinker<T>

/** Predefined shrinkers */

class MutableListShrinker<T>(
        val shrinker: Shrinker<T>,
        sizeBound: Int,
        fixedSize: Boolean
) : ContainerShrinker<MutableList<T>>(sizeBound, fixedSize) {
    override fun shrink(
            test: (MutableList<T>) -> Boolean,
            counterexample: MutableList<T>
    ): MutableList<T> {
        var shrinked = ArrayList(counterexample)

        if (!fixedSize) {
            val intShrinker = IntShrinker(0, sizeBound)
            val newSize = intShrinker.shrink(
                    { test(counterexample.subList(0, it)) },
                    shrinked.size
            )
            shrinked = ArrayList(shrinked.subList(0, newSize))
        }


        for (i in shrinked.indices) {
            shrinked[i] = shrinker.shrink(
                    {
                        shrinked[i] = it
                        test(shrinked)
                    },
                    shrinked[i]
            )
        }

        return shrinked
    }
}

class StringShrinker(
        lengthBound: Int,
        fixedLength: Boolean
) : ContainerShrinker<String>(lengthBound, fixedLength) {
    private val charsShrinker = MutableListShrinker(
            CharShrinker(
                    Random.PRINTABLE_ASCII_ORIGIN.toChar(),
                    Random.PRINTABLE_ASCII_BOUND.toChar()
            ),
            lengthBound,
            fixedLength
    )

    override fun shrink(
            test: (String) -> Boolean,
            counterexample: String
    ): String = charsShrinker.shrink(
            { test(it.joinToString("")) },
            counterexample.toMutableList()
    ).joinToString("")
}
