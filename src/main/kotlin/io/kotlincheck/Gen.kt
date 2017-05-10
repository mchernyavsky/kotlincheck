package io.kotlincheck

import io.kotlincheck.shrink.*
import io.kotlincheck.shrink.containers.*
import java.math.BigDecimal
import java.math.BigInteger


abstract class Gen<T>(private val shrinker: Shrinker<T>?) {
    abstract fun generate(): T

    companion object {
        private val DEFAULT_COLLECTION_SIZE_BOUND = 100

        inline fun <reified T> create(
                shrinker: Shrinker<T>?,
                crossinline body: () -> T
        ): Gen<T> = object : Gen<T>(shrinker) {
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

        fun booleans(): Gen<Boolean> = create(
                BooleanShrinker(),
                { Random.nextBoolean() }
        )

        fun bytes(
                origin: Byte = Byte.MIN_VALUE,
                bound: Byte = Byte.MAX_VALUE
        ): Gen<Byte> = create(
                ByteShrinker(origin, bound),
                { Random.nextByte(origin, bound) }
        )

        fun chars(
                origin: Char = Char.MIN_SURROGATE,
                bound: Char = Char.MAX_SURROGATE
        ): Gen<Char> = create(
                CharShrinker(origin, bound),
                { Random.nextChar(origin, bound) }
        )

        fun printableChars(): Gen<Char> = create(
                CharShrinker(
                        Random.PRINTABLE_ASCII_ORIGIN.toChar(),
                        Random.PRINTABLE_ASCII_BOUND.toChar()
                ),
                { Random.nextPrintableChar() }
        )

        fun strings(
                lengthBound: Int = DEFAULT_COLLECTION_SIZE_BOUND,
                fixedLength: Boolean = false
        ): Gen<String> = object : Gen<String>(StringShrinker(lengthBound, fixedLength)) {
            private val charListsGen = lists(printableChars(), lengthBound, fixedLength)
            override fun generate(): String = charListsGen.generate().joinToString("")
        }

        fun shorts(
                origin: Short = Short.MIN_VALUE,
                bound: Short = Short.MAX_VALUE
        ): Gen<Short> = create(
                ShortShrinker(origin, bound),
                { Random.nextShort(origin, bound) }
        )

        fun ints(
                origin: Int = Int.MIN_VALUE,
                bound: Int = Int.MAX_VALUE
        ): Gen<Int> = create(
                IntShrinker(origin, bound),
                { Random.nextInt(origin, bound) }
        )

        fun longs(
                origin: Long = Long.MIN_VALUE,
                bound: Long = Long.MAX_VALUE
        ): Gen<Long> = create(
                LongShrinker(origin, bound),
                { Random.nextLong(origin, bound) }
        )

        fun bigIntegers(
                origin: BigInteger,
                bound: BigInteger
        ): Gen<BigInteger> = create(
                BigIntegerShrinker(origin, bound),
                { Random.nextBigInteger(origin, bound) }
        )

        fun floats(
                origin: Float = Float.MIN_VALUE,
                bound: Float = Float.MAX_VALUE
        ): Gen<Float> = create(
                FloatShrinker(origin, bound),
                { Random.nextFloat(origin, bound) }
        )

        fun doubles(
                origin: Double = Double.MIN_VALUE,
                bound: Double = Double.MAX_VALUE
        ): Gen<Double> = create(
                DoubleShrinker(origin, bound),
                { Random.nextDouble(origin, bound) }
        )

        fun gaussians(): Gen<Double> = create(
                DoubleShrinker(0.0, 1.0),
                { Random.nextGaussian() }
        )

        fun bigDecimals(origin: BigDecimal, bound: BigDecimal): Gen<BigDecimal> = create(
                BigDecimalShrinker(origin, bound),
                { Random.nextBigDecimal(origin, bound) }
        )

        inline fun <reified T> oneOf(values: List<T>): Gen<T> = create(
                OneOfShrinker(values),
                { values[Random.nextInt(0, values.size)] }
        )

        inline fun <reified T> oneOf(values: Array<T>): Gen<T> = create(
                OneOfShrinker(values.asList()),
                { values[Random.nextInt(0, values.size)] }
        )

        inline fun <reified T> oneOf(values: Set<T>): Gen<T> = oneOf(values.toList())

        fun <T> oneOf(vararg generators: Gen<T>): Gen<T> = object : Gen<T>(null) {
            private val oneOfGens = oneOf(generators.toList())
            override fun generate(): T = oneOfGens.generate().generate()
        }

        inline fun <reified T> nullable(gen: Gen<T?>): Gen<T?> = oneOf(create<T?>(null) { null }, gen)

        fun <A, B> pairs(
                gen1: Gen<A>,
                gen2: Gen<B>): Gen<Pair<A, B>> = create(
                PairShrinker(gen1.shrinker, gen2.shrinker),
                { Pair(gen1.generate(), gen2.generate()) }
        )

        fun <A, B, C> triplets(gen1: Gen<A>, gen2: Gen<B>, gen3: Gen<C>): Gen<Triple<A, B, C>> = create(
                TripleShrinker(gen1.shrinker, gen2.shrinker, gen3.shrinker),
                { Triple(gen1.generate(), gen2.generate(), gen3.generate()) }
        )


        /** Container generators */

        fun <T> lists(
                gen: Gen<T>,
                sizeBound: Int = DEFAULT_COLLECTION_SIZE_BOUND,
                fixedSize: Boolean = false
        ): Gen<List<T>> = create(
                ListShrinker(gen.shrinker, sizeBound, fixedSize),
                {
                    val size = if (fixedSize) sizeBound else Random.nextInt(0, sizeBound + 1)
                    (0 until size).map { gen.generate() }
                }
        )

        fun <T> mutableLists(
                gen: Gen<T>,
                sizeBound: Int = DEFAULT_COLLECTION_SIZE_BOUND,
                fixedSize: Boolean = false
        ): Gen<MutableList<T>> = create(
                MutableListShrinker(gen.shrinker, sizeBound, fixedSize),
                {
                    val size = if (fixedSize) sizeBound else Random.nextInt(0, sizeBound + 1)
                    (0 until size).map { gen.generate() }.toMutableList()
                }
        )

        fun <T> sets(
                gen: Gen<T>,
                sizeBound: Int = DEFAULT_COLLECTION_SIZE_BOUND,
                fixedSize: Boolean = false
        ): Gen<Set<T>> = object : Gen<Set<T>>(null) {
            private val setsGen = mutableSets(gen, sizeBound, fixedSize)
            override fun generate(): Set<T> = setsGen.generate().toSet()
        }

        fun <T> mutableSets(
                gen: Gen<T>,
                sizeBound: Int = DEFAULT_COLLECTION_SIZE_BOUND,
                fixedSize: Boolean = false
        ): Gen<MutableSet<T>> = create(null) {
            val expectedSize = if (fixedSize) sizeBound else Random.nextInt(0, sizeBound)
            val set = mutableSetOf<T>()
            while (set.size < expectedSize) {
                set.add(gen.generate())
            }
            set
        }

        fun <K, V> maps(
                keyGen: Gen<K>,
                valueGen: Gen<V>,
                sizeBound: Int = DEFAULT_COLLECTION_SIZE_BOUND,
                fixedSize: Boolean = false
        ): Gen<Map<K, V>> = object : Gen<Map<K, V>>(null) {
            private val mapsGen = mutableMaps(keyGen, valueGen, sizeBound, fixedSize)
            override fun generate(): Map<K, V> = mapsGen.generate().toMap()
        }

        fun <K, V> mutableMaps(
                keyGen: Gen<K>,
                valueGen: Gen<V>,
                sizeBound: Int = DEFAULT_COLLECTION_SIZE_BOUND,
                fixedSize: Boolean = false
        ): Gen<MutableMap<K, V>> = create(null) {
            val expectedSize = if (fixedSize) sizeBound else Random.nextInt(0, sizeBound)
            val map = mutableMapOf<K, V>()
            while (map.size < expectedSize) {
                map.put(keyGen.generate(), valueGen.generate())
            }
            map
        }
    }
}
