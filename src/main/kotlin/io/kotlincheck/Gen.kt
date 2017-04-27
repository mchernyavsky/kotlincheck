package io.kotlincheck

import java.math.BigDecimal
import java.math.BigInteger


interface Gen<out T> {
    fun generate(): T

    companion object {
        private val DEFAULT_COLLECTION_SIZE_BOUND = 100

        inline fun <T> create(crossinline body: () -> T): Gen<T> = object : Gen<T> {
            override fun generate(): T = body()
        }

        @Suppress("unchecked_cast")
        inline fun <reified T> default(): Gen<T> = when {
            Collection::class.java.isAssignableFrom(T::class.java) -> {
                val type = object : TypeParameterProvider<T> {}.getNthTypeParameter(0)
                val gen = forClassName(type.typeName)
                when (T::class) {
                    List::class -> lists(gen) as Gen<T>
                    MutableList::class -> mutableLists(gen) as Gen<T>
                    Set::class -> sets(gen) as Gen<T>
                    MutableSet::class -> mutableSets(gen) as Gen<T>
                    else -> throw IllegalArgumentException("cannot infer generator for " +
                                                           "${T::class.qualifiedName}")
                }
            }
            Map::class.java.isAssignableFrom(T::class.java) -> {
                val keyType = object : TypeParameterProvider<T> {}.getNthTypeParameter(0)
                val valueType = object : TypeParameterProvider<T> {}.getNthTypeParameter(1)
                val keyGen = forClassName(keyType.typeName)
                val valueGen = forClassName(valueType.typeName)
                when (T::class) {
                    Map::class -> maps(keyGen, valueGen) as Gen<T>
                    MutableMap::class -> mutableMaps(keyGen, valueGen) as Gen<T>
                    else -> throw IllegalArgumentException("cannot infer generator for " +
                                                           "${T::class.qualifiedName}")
                }
            }
            else -> forClassName(T::class.qualifiedName!!) as Gen<T>
        }

        fun forClassName(className: String): Gen<*> = when (className) {
            "java.lang.Boolean" -> booleans()
            "kotlin.Boolean" -> booleans()
            "java.lang.Byte" -> bytes()
            "kotlin.Byte" -> bytes()
            "java.lang.Char" -> chars()
            "kotlin.Char" -> chars()
            "java.lang.Short" -> shorts()
            "kotlin.Short" -> shorts()
            "java.lang.Integer" -> ints()
            "kotlin.Int" -> ints()
            "java.lang.Long" -> longs()
            "kotlin.Long" -> longs()
            "java.lang.Float" -> floats()
            "kotlin.Float" -> floats()
            "java.lang.Double" -> doubles()
            "kotlin.Double" -> doubles()
            "java.lang.String" -> strings()
            "kotlin.String" -> strings()
            else -> throw IllegalArgumentException("cannot infer generator for $className")
        }

        fun booleans(): Gen<Boolean> = create { Random.nextBoolean() }

        fun bytes(origin: Byte = Byte.MIN_VALUE, bound: Byte = Byte.MAX_VALUE): Gen<Byte> =
                create { Random.nextByte(origin, bound) }

        fun chars(origin: Char = Char.MIN_SURROGATE, bound: Char = Char.MAX_SURROGATE): Gen<Char> =
                create { Random.nextChar(origin, bound) }

        fun printableChars(): Gen<Char> = create { Random.nextPrintableChar() }

        fun strings(lengthBound: Int = DEFAULT_COLLECTION_SIZE_BOUND,
                    fixedLength: Boolean = false): Gen<String> = object : Gen<String> {
            private val charListsGen = lists(printableChars(), lengthBound, fixedLength)
            override fun generate(): String = charListsGen.generate().joinToString("")
        }

        fun shorts(origin: Short = Short.MIN_VALUE, bound: Short = Short.MAX_VALUE): Gen<Short> =
                create { Random.nextShort(origin, bound) }

        fun ints(origin: Int = Int.MIN_VALUE, bound: Int = Int.MAX_VALUE): Gen<Int> =
                create { Random.nextInt(origin, bound) }

        fun longs(origin: Long = Long.MIN_VALUE, bound: Long = Long.MAX_VALUE): Gen<Long> =
                create { Random.nextLong(origin, bound) }

        fun bigIntegers(origin: BigInteger, bound: BigInteger): Gen<BigInteger> =
                create { Random.nextBigInteger(origin, bound) }

        fun floats(origin: Float = Float.MIN_VALUE, bound: Float = Float.MAX_VALUE): Gen<Float> =
                create { Random.nextFloat(origin, bound) }

        fun doubles(origin: Double = Double.MIN_VALUE, bound: Double = Double.MAX_VALUE): Gen<Double> =
                create { Random.nextDouble(origin, bound) }

        fun gaussians(): Gen<Double> = create { Random.nextGaussian() }

        fun bigDecimals(origin: BigDecimal, bound: BigDecimal): Gen<BigDecimal> =
                create { Random.nextBigDecimal(origin, bound) }

        fun <T> oneOf(values: List<T>): Gen<T> = create { values[Random.nextInt(0, values.size)] }

        fun <T> oneOf(values: Array<T>): Gen<T> = create { values[Random.nextInt(0, values.size)] }

        fun <T> oneOf(values: Set<T>): Gen<T> = oneOf(values.toList())

        fun <T> oneOf(vararg generators: Gen<T>): Gen<T> = object : Gen<T> {
            private val oneOfGens = oneOf(generators.toList())
            override fun generate(): T = oneOfGens.generate().generate()
        }

        fun <T> nullable(gen: Gen<T>): Gen<T?> = oneOf(create { null }, gen)

        fun <A, B> pairs(gen1: Gen<A>, gen2: Gen<B>): Gen<Pair<A, B>> =
                create { Pair(gen1.generate(), gen2.generate()) }

        fun <A, B, C> triplets(gen1: Gen<A>, gen2: Gen<B>, gen3: Gen<C>): Gen<Triple<A, B, C>> =
                create { Triple(gen1.generate(), gen2.generate(), gen3.generate()) }


        /** Collection generators */

        fun <T> lists(gen: Gen<T>,
                      sizeBound: Int = DEFAULT_COLLECTION_SIZE_BOUND,
                      fixedSize: Boolean = false): Gen<List<T>> = create {
            val size = if (fixedSize) sizeBound else Random.nextInt(0, sizeBound + 1)
            (0 until size).map { gen.generate() }
        }

        fun <T> mutableLists(gen: Gen<T>,
                             sizeBound: Int = DEFAULT_COLLECTION_SIZE_BOUND,
                             fixedSize: Boolean = false): Gen<MutableList<T>> = object : Gen<MutableList<T>> {
            private val listsGen = lists(gen, sizeBound, fixedSize)
            override fun generate(): MutableList<T> = listsGen.generate().toMutableList()
        }

        fun <T> sets(gen: Gen<T>,
                     sizeBound: Int = DEFAULT_COLLECTION_SIZE_BOUND,
                     fixedSize: Boolean = false): Gen<Set<T>> = object : Gen<Set<T>> {
            private val setsGen = mutableSets(gen, sizeBound, fixedSize)
            override fun generate(): Set<T> = setsGen.generate().toSet()
        }

        fun <T> mutableSets(gen: Gen<T>,
                            sizeBound: Int = DEFAULT_COLLECTION_SIZE_BOUND,
                            fixedSize: Boolean = false): Gen<MutableSet<T>> = create {
            val expectedSize = if (fixedSize) sizeBound else Random.nextInt(0, sizeBound)
            val set = mutableSetOf<T>()
            while (set.size < expectedSize) {
                set.add(gen.generate())
            }
            set
        }

        fun <K, V> maps(keyGen: Gen<K>, valueGen: Gen<V>,
                        sizeBound: Int = DEFAULT_COLLECTION_SIZE_BOUND,
                        fixedSize: Boolean = false): Gen<Map<K, V>> = object : Gen<Map<K, V>> {
            private val mapsGen = mutableMaps(keyGen, valueGen, sizeBound, fixedSize)
            override fun generate(): Map<K, V> = mapsGen.generate().toMap()
        }

        fun <K, V> mutableMaps(keyGen: Gen<K>, valueGen: Gen<V>,
                               sizeBound: Int = DEFAULT_COLLECTION_SIZE_BOUND,
                               fixedSize: Boolean = false): Gen<MutableMap<K, V>> = create {
            val expectedSize = if (fixedSize) sizeBound else Random.nextInt(0, sizeBound)
            val map = mutableMapOf<K, V>()
            while (map.size < expectedSize) {
                map.put(keyGen.generate(), valueGen.generate())
            }
            map
        }
    }
}
