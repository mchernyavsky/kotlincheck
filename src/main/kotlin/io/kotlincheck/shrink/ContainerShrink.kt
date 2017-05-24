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
        var counterNew = ArrayList(counterexample)

        do {
            val counterOld = ArrayList(counterNew)
            if (!fixedSize) {
                val intShrinker = IntShrinker(0, sizeBound)

                val newSize = intShrinker.shrink(
                        { test(counterNew.subList(0, it)) },
                        counterNew.size
                )
                counterNew = ArrayList(counterNew.subList(0, newSize))

                val counterNewReversed = counterNew.asReversed()
                if (!test(counterNewReversed)) {
                    val newSizeReversed = intShrinker.shrink(
                            { test(counterNewReversed.subList(0, it)) },
                            counterNew.size
                    )
                    counterNew = ArrayList(counterNewReversed.subList(0, newSizeReversed).asReversed())
                }
            }


            for (i in counterNew.indices) {
                counterNew[i] = shrinker.shrink(
                        {
                            counterNew[i] = it
                            test(counterNew)
                        },
                        counterNew[i]
                )
            }
        } while (counterOld != counterNew)

        return counterNew
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
