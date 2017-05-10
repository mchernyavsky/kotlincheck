package io.kotlincheck.gen.containers

import io.kotlincheck.Random
import io.kotlincheck.gen.Gen
import io.kotlincheck.shrink.Shrinker
import io.kotlincheck.shrink.containers.ListShrinker

class ListGen<T>(
        val gen: Gen<T>,
        val sizeBound: Int = Gen.DEFAULT_CONTAINER_SIZE_BOUND,
        val fixedSize: Boolean = false,
        shrinker: Shrinker<List<T>>? = ListShrinker(gen.shrinker, sizeBound, fixedSize)
) : Gen<List<T>>(shrinker) {
    override fun generate(): List<T> {
        val size = if (fixedSize) sizeBound else Random.nextInt(0, sizeBound)
        return List(size) { gen.generate() }
    }
}
