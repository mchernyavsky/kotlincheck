package io.kotlincheck.gen

import io.kotlincheck.Random

class MutableListGen<T>(
    val gen: Gen<T>,
    val sizeBound: Int,
    val fixedSize: Boolean
) : Gen<MutableList<T>> {

    override fun generate(): MutableList<T> {
        val size = if (fixedSize) sizeBound else Random.nextInt(0, sizeBound)
        return MutableList(size) { gen.generate() }
    }

    override fun isAcceptable(value: MutableList<T>): Boolean {
        if (value.size !in 0..sizeBound) return false
        if (fixedSize && value.size != sizeBound) return false
        return value.all { gen.isAcceptable(it) }
    }
}

class MutableSetGen<T>(
    val gen: Gen<T>,
    val sizeBound: Int,
    val fixedSize: Boolean
) : Gen<MutableSet<T>> {

    override fun generate(): MutableSet<T> {
        val expectedSize = if (fixedSize) sizeBound else Random.nextInt(0, sizeBound)
        val set = mutableSetOf<T>()
        while (set.size < expectedSize) {
            set.add(gen.generate())
        }
        return set
    }

    override fun isAcceptable(value: MutableSet<T>): Boolean {
        if (value.size !in 0..sizeBound) return false
        if (fixedSize && value.size != sizeBound) return false
        return value.all { gen.isAcceptable(it) }
    }
}

class MutableMapGen<K, V>(
    val keyGen: Gen<K>,
    val valueGen: Gen<V>,
    val sizeBound: Int,
    val fixedSize: Boolean
) : Gen<MutableMap<K, V>> {

    override fun generate(): MutableMap<K, V> {
        val expectedSize = if (fixedSize) sizeBound else Random.nextInt(0, sizeBound)
        val map = mutableMapOf<K, V>()
        while (map.size < expectedSize) {
            map[keyGen.generate()] = valueGen.generate()
        }
        return map
    }

    override fun isAcceptable(value: MutableMap<K, V>): Boolean {
        if (value.size !in 0..sizeBound) return false
        if (fixedSize && value.size != sizeBound) return false
        return value.all { (k, v) -> keyGen.isAcceptable(k) && valueGen.isAcceptable(v) }
    }
}

class StringGen(val lengthBound: Int, val fixedLength: Boolean) : Gen<String> {
    private val charListsGen: MutableListGen<Char> = MutableListGen(
        CharGen(Random.PRINTABLE_ASCII_ORIGIN.toChar(), Random.PRINTABLE_ASCII_BOUND.toChar()),
        lengthBound,
        fixedLength
    )

    override fun generate(): String = charListsGen.generate().joinToString("")

    override fun isAcceptable(value: String): Boolean = charListsGen.isAcceptable(value.toMutableList())
}
