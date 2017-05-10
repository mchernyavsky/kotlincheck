package io.kotlincheck.shrink.containers

import io.kotlincheck.shrink.IntShrinker
import io.kotlincheck.shrink.Shrinker

class MutableListShrinker<T>(
        val shrinker: Shrinker<T>?,
        val sizeBound: Int,
        val fixedSize: Boolean
) : Shrinker<MutableList<T>> {
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


        shrinker?.let {
            for (i in shrinked.indices) {
                shrinked[i] = shrinker.shrink(
                        {
                            shrinked[i] = it
                            test(shrinked)
                        },
                        shrinked[i]
                )
            }
        }

        return shrinked
    }
}
