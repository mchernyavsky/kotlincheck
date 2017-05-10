package io.kotlincheck.shrink

interface Shrinker<T> {
    fun shrink(test: (T) -> Boolean, counterexample: T): T
}
