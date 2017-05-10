package io.kotlincheck.gen.containers

import io.kotlincheck.gen.Gen
import io.kotlincheck.shrink.Shrinker

class SetGen<T>(
        val gen: Gen<T>,
        val sizeBound: Int = Gen.DEFAULT_CONTAINER_SIZE_BOUND,
        val fixedSize: Boolean = false,
        shrinker: Shrinker<Set<T>>? = null
) : Gen<Set<T>>(shrinker) {
    private val mutableSetGen = MutableSetGen(gen, sizeBound, fixedSize)

    override fun generate(): Set<T> = mutableSetGen.generate().toSet()
}
