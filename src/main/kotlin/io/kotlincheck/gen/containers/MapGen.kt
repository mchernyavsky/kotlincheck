package io.kotlincheck.gen.containers

import io.kotlincheck.gen.Gen
import io.kotlincheck.shrink.Shrinker

class MapGen<K, V>(
        val keyGen: Gen<K>,
        val valueGen: Gen<V>,
        val sizeBound: Int = Gen.DEFAULT_CONTAINER_SIZE_BOUND,
        val fixedSize: Boolean = false,
        shrinker: Shrinker<Map<K, V>>? = null
) : Gen<Map<K, V>>(shrinker) {
    private val mutableSetGen = MutableMapGen(keyGen, valueGen, sizeBound, fixedSize)

    override fun generate(): Map<K, V> = mutableSetGen.generate()
}