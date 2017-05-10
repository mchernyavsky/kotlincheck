package io.kotlincheck.gen

import io.kotlincheck.TypeParameterProvider
import io.kotlincheck.gen.containers.*
import io.kotlincheck.shrink.Shrinker


abstract class Gen<T>(val shrinker: Shrinker<T>?) {
    abstract fun generate(): T

    companion object {
        val DEFAULT_CONTAINER_SIZE_BOUND = 100

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
                    List::class -> ListGen(gen) as Gen<T>
                    MutableList::class -> MutableListGen(gen) as Gen<T>
                    Set::class -> SetGen(gen) as Gen<T>
                    MutableSet::class -> MutableSetGen(gen) as Gen<T>
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
                    Map::class -> MapGen(keyGen, valueGen) as Gen<T>
                    MutableMap::class -> MutableMapGen(keyGen, valueGen) as Gen<T>
                    else -> throw IllegalArgumentException("cannot infer generator for " +
                                                           "${T::class.qualifiedName}")
                }
            }
            else -> forClassName(T::class.qualifiedName!!) as Gen<T>
        }

        fun forClassName(className: String): Gen<*> = when (className) {
            "java.lang.Boolean" -> BooleanGen()
            "kotlin.Boolean" -> BooleanGen()
            "java.lang.Byte" -> ByteGen()
            "kotlin.Byte" -> ByteGen()
            "java.lang.Char" -> CharGen()
            "kotlin.Char" -> CharGen()
            "java.lang.Short" -> ShortGen()
            "kotlin.Short" -> ShortGen()
            "java.lang.Integer" -> IntGen()
            "kotlin.Int" -> IntGen()
            "java.lang.Long" -> LongGen()
            "kotlin.Long" -> LongGen()
            "java.lang.Float" -> FloatGen()
            "kotlin.Float" -> FloatGen()
            "java.lang.Double" -> DoubleGen()
            "kotlin.Double" -> DoubleGen()
            "java.lang.String" -> StringGen()
            "kotlin.String" -> StringGen()
            else -> throw IllegalArgumentException("cannot infer generator for $className")
        }
    }
}
