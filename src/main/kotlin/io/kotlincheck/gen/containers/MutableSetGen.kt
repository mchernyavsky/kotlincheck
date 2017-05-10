package io.kotlincheck.gen.containers

import io.kotlincheck.Random
import io.kotlincheck.gen.Gen
import io.kotlincheck.shrink.Shrinker

class MutableSetGen<T>(
        val gen: Gen<T>,
        val sizeBound: Int = Gen.DEFAULT_CONTAINER_SIZE_BOUND,
        val fixedSize: Boolean = false,
        shrinker: Shrinker<MutableSet<T>>? = null
) : Gen<MutableSet<T>>(shrinker) {
    override fun generate(): MutableSet<T> {
        val expectedSize = if (fixedSize) sizeBound else Random.nextInt(0, sizeBound)
        val set = mutableSetOf<T>()
        while (set.size < expectedSize) {
            set.add(gen.generate())
        }
        return set
    }
}
