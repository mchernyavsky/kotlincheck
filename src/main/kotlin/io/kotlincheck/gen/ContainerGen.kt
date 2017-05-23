package io.kotlincheck.gen

import io.kotlincheck.Random

class MutableListGen<T>(val gen: Gen<T>, val sizeBound: Int, val fixedSize: Boolean
) : Gen<MutableList<T>> {
    override fun generate(): MutableList<T> {
        val size = if (fixedSize) sizeBound else Random.nextInt(0, sizeBound)
        return MutableList(size) { gen.generate() }
    }
}

class MutableSetGen<T>(val gen: Gen<T>, val sizeBound: Int, val fixedSize: Boolean
) : Gen<MutableSet<T>> {
    override fun generate(): MutableSet<T> {
        val expectedSize = if (fixedSize) sizeBound else Random.nextInt(0, sizeBound)
        val set = mutableSetOf<T>()
        while (set.size < expectedSize) {
            set.add(gen.generate())
        }
        return set
    }
}

class MutableMapGen<K, V>(val keyGen: Gen<K>, val valueGen: Gen<V>, val sizeBound: Int, val fixedSize: Boolean
) : Gen<MutableMap<K, V>> {
    override fun generate(): MutableMap<K, V> {
        val expectedSize = if (fixedSize) sizeBound else Random.nextInt(0, sizeBound)
        val map = mutableMapOf<K, V>()
        while (map.size < expectedSize) {
            map.put(keyGen.generate(), valueGen.generate())
        }
        return map
    }
}

class StringGen(val lengthBound: Int, val fixedLength: Boolean
) : Gen<String> {
    private val charListsGen = MutableListGen(
            CharGen(Random.PRINTABLE_ASCII_ORIGIN.toChar(), Random.PRINTABLE_ASCII_BOUND.toChar()),
            lengthBound,
            fixedLength
    )

    override fun generate(): String = charListsGen.generate().joinToString("")
}
