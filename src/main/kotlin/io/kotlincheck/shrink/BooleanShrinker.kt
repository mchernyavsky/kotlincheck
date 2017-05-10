package io.kotlincheck.shrink

class BooleanShrinker : Shrinker<Boolean> {
    override fun shrink(
            test: (Boolean) -> Boolean,
            counterexample: Boolean
    ): Boolean = if (!counterexample) false else test(false)
}
