package io.kotlincheck.gen

import io.kotlincheck.Random
import io.kotlincheck.shrink.OneOfShrinker
import io.kotlincheck.shrink.Shrinker

class OneOfGen <T>(
        val values: List<T>,
        shrinker: Shrinker<T>? = OneOfShrinker(values)
) : Gen<T>(shrinker) {

    constructor(
            values: Array<T>,
            shrinker: Shrinker<T>? = OneOfShrinker(values.asList())
    ) : this(values.asList(), shrinker)

    constructor(
            values: Set<T>,
            shrinker: Shrinker<T>? = OneOfShrinker(values.toList())
    ) : this(values.toList(), shrinker)

    override fun generate(): T = values[Random.nextInt(0, values.size)]
}
