package io.kotlincheck.gen.containers

import io.kotlincheck.Random
import io.kotlincheck.gen.Gen
import io.kotlincheck.shrink.Shrinker

class MutableMapGen<K, V>(
        val keyGen: Gen<K>,
        val valueGen: Gen<V>,
        val sizeBound: Int = Gen.DEFAULT_CONTAINER_SIZE_BOUND,
        val fixedSize: Boolean = false,
        shrinker: Shrinker<MutableMap<K, V>>? = null
) : Gen<MutableMap<K, V>>(shrinker) {
    override fun generate(): MutableMap<K, V> {
        val expectedSize = if (fixedSize) sizeBound else Random.nextInt(0, sizeBound)
        val map = mutableMapOf<K, V>()
        while (map.size < expectedSize) {
            map.put(keyGen.generate(), valueGen.generate())
        }
        return map
    }
}