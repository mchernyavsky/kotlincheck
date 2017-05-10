package io.kotlincheck.shrink.containers

import io.kotlincheck.shrink.Shrinker

class ListShrinker<T>(
        val shrinker: Shrinker<T>?,
        val sizeBound: Int,
        val fixedSize: Boolean
) : Shrinker<List<T>> {
    private val mutableListShrinker = MutableListShrinker(
            shrinker,
            sizeBound,
            fixedSize
    )

    override fun shrink(
            test: (List<T>) -> Boolean,
            counterexample: List<T>
    ): List<T> = mutableListShrinker.shrink(
            test,
            counterexample.toMutableList()
    )
}
