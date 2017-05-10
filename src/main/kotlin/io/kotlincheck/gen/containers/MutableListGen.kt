package io.kotlincheck.gen.containers

import io.kotlincheck.Random
import io.kotlincheck.gen.Gen
import io.kotlincheck.shrink.Shrinker
import io.kotlincheck.shrink.containers.MutableListShrinker

class MutableListGen<T>(
        val gen: Gen<T>,
        val sizeBound: Int = Gen.DEFAULT_CONTAINER_SIZE_BOUND,
        val fixedSize: Boolean = false,
        shrinker: Shrinker<MutableList<T>>? = MutableListShrinker(gen.shrinker, sizeBound, fixedSize)
) : Gen<MutableList<T>>(shrinker) {
    override fun generate(): MutableList<T> {
        val size = if (fixedSize) sizeBound else Random.nextInt(0, sizeBound)
        return MutableList(size) { gen.generate() }
    }
}
