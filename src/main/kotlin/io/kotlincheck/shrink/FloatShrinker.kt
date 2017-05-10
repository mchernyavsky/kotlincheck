package io.kotlincheck.shrink

class FloatShrinker(val origin: Float, val bound: Float) : Shrinker<Float> {
    private val doubleShrinker = DoubleShrinker(origin.toDouble(), bound.toDouble())

    override fun shrink(
            test: (Float) -> Boolean,
            counterexample: Float
    ): Float = doubleShrinker.shrink(
            { test(it.toFloat()) },
            counterexample.toDouble()
    ).toFloat()
}
