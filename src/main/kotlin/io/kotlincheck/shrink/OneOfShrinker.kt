package io.kotlincheck.shrink

class OneOfShrinker<T>(val values: List<T>) : Shrinker<T> {
    override fun shrink(
            test: (T) -> Boolean,
            counterexample: T
    ): T = values.find { !test(it) }!!
}
