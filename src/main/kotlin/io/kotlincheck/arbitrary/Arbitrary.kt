package io.kotlincheck.arbitrary

import io.kotlincheck.TypeParameterProvider
import io.kotlincheck.gen.Gen
import io.kotlincheck.shrink.Shrinker

abstract class Arbitrary<T>(
        val gen: Gen<T>,
        val shrinker: Shrinker<T>
) : Gen<T> by gen, Shrinker<T> by shrinker {

    companion object {
        val DEFAULT_CONTAINER_SIZE_BOUND = 300

        @Suppress("unchecked_cast")
        inline fun <reified T> default(): Arbitrary<T> = when {
            Collection::class.java.isAssignableFrom(T::class.java) -> {
                val type = object : TypeParameterProvider<T> {}.getNthTypeParameter(0)
                val elemArb = forClassName(type.typeName)
                when (T::class) {
                    List::class -> ListArbitrary(elemArb) as Arbitrary<T>
                    MutableList::class -> MutableListArbitrary(elemArb) as Arbitrary<T>
                    Set::class -> SetArbitrary(elemArb) as Arbitrary<T>
                    MutableSet::class -> MutableSetArbitrary(elemArb) as Arbitrary<T>
                    else -> throw IllegalArgumentException("Cannot infer arbitrary for " +
                                                           "${T::class.qualifiedName}")
                }
            }
            Map::class.java.isAssignableFrom(T::class.java) -> {
                val keyType = object : TypeParameterProvider<T> {}.getNthTypeParameter(0)
                val valueType = object : TypeParameterProvider<T> {}.getNthTypeParameter(1)
                val keyArb = forClassName(keyType.typeName)
                val valueArb = forClassName(valueType.typeName)
                when (T::class) {
                    Map::class -> MapArbitrary(keyArb, valueArb) as Arbitrary<T>
                    MutableMap::class -> MutableMapArbitrary(keyArb, valueArb) as Arbitrary<T>
                    else -> throw IllegalArgumentException("Cannot infer arbitrary for " +
                                                           "${T::class.qualifiedName}")
                }
            }
            else -> forClassName(T::class.qualifiedName!!) as Arbitrary<T>
        }

        fun forClassName(className: String): Arbitrary<*> = when (className) {
            "java.lang.Long", "kotlin.Long" -> LongArbitrary()
            "java.lang.Integer", "kotlin.Int" -> IntArbitrary()
            "java.lang.Short", "kotlin.Short" -> ShortArbitrary()
            "java.lang.Byte", "kotlin.Byte" -> ByteArbitrary()
            "java.lang.Char", "kotlin.Char" -> CharArbitrary()
            "java.lang.Boolean", "kotlin.Boolean" -> BooleanArbitrary()
            "java.lang.Double", "kotlin.Double" -> DoubleArbitrary()
            "java.lang.Float", "kotlin.Float" -> FloatArbitrary()
            "java.lang.String", "kotlin.String" -> StringArbitrary()
            else -> throw IllegalArgumentException("Cannot infer arbitrary for $className")
        }
    }
}
