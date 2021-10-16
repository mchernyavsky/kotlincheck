package io.kotlincheck.arbitrary

import io.kotlincheck.TypeParameterProvider
import io.kotlincheck.gen.Gen
import io.kotlincheck.shrink.DummyShrinker
import io.kotlincheck.shrink.Shrinker
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.full.isSuperclassOf
import kotlin.reflect.full.withNullability
import kotlin.reflect.jvm.jvmErasure

open class Arbitrary<T>(
    val gen: Gen<T>,
    val shrinker: Shrinker<T> = DummyShrinker()
) : Gen<T> by gen, Shrinker<T> by shrinker {

    @Suppress("UNCHECKED_CAST")
    companion object {
        const val DEFAULT_CONTAINER_SIZE_BOUND: Int = 100
        const val DEFAULT_MAX_RECURSION_DEPTH: Int = 5

        inline fun <reified T> default(): Arbitrary<T> = when {
            Collection::class.isSuperclassOf(T::class) -> {
                val type = TypeParameterProvider.getNthTypeParameter<T>(0)
                val elemArb = forClassName(type.typeName)
                when (T::class) {
                    List::class, MutableList::class -> MutableListArbitrary(elemArb) as Arbitrary<T>
                    Set::class, MutableSet::class -> MutableSetArbitrary(elemArb) as Arbitrary<T>
                    else -> throw IllegalArgumentException("Cannot infer arbitrary for ${T::class.qualifiedName}")
                }
            }
            Map::class.isSuperclassOf(T::class) -> {
                val keyType = TypeParameterProvider.getNthTypeParameter<T>(0)
                val keyArb = forClassName(keyType.typeName)
                val valueType = TypeParameterProvider.getNthTypeParameter<T>(1)
                val valueArb = forClassName(valueType.typeName)
                when (T::class) {
                    Map::class, MutableMap::class -> MutableMapArbitrary(keyArb, valueArb) as Arbitrary<T>
                    else -> throw IllegalArgumentException("Cannot infer arbitrary for ${T::class.qualifiedName}")
                }
            }
            Pair::class.isSuperclassOf(T::class) -> {
                val firstType = TypeParameterProvider.getNthTypeParameter<T>(0)
                val firstArb = forClassName(firstType.typeName)
                val secondType = TypeParameterProvider.getNthTypeParameter<T>(1)
                val secondArb = forClassName(secondType.typeName)
                PairArbitrary(firstArb, secondArb) as Arbitrary<T>
            }
            Triple::class.isSuperclassOf(T::class) -> {
                val firstType = TypeParameterProvider.getNthTypeParameter<T>(0)
                val firstArb = forClassName(firstType.typeName)
                val secondType = TypeParameterProvider.getNthTypeParameter<T>(1)
                val secondArb = forClassName(secondType.typeName)
                val thirdType = TypeParameterProvider.getNthTypeParameter<T>(2)
                val thirdArb = forClassName(thirdType.typeName)
                TripleArbitrary(firstArb, secondArb, thirdArb) as Arbitrary<T>
            }
            else -> forClassName(T::class.qualifiedName!!) as Arbitrary<T>
        }

        fun forClassName(
            className: String,
            usedClasses: Set<KClass<*>> = setOf(),
            recursionDeep: Int = 0
        ): Arbitrary<*> = when (className) {
            "java.lang.Long", "kotlin.Long" -> LongArbitrary()
            "java.lang.Integer", "kotlin.Int" -> IntArbitrary()
            "java.lang.Short", "kotlin.Short" -> ShortArbitrary()
            "java.lang.Byte", "kotlin.Byte" -> ByteArbitrary()
            "java.lang.Char", "kotlin.Char" -> CharArbitrary()
            "java.lang.Boolean", "kotlin.Boolean" -> BooleanArbitrary()
            "java.lang.Double", "kotlin.Double" -> DoubleArbitrary()
            "java.lang.Float", "kotlin.Float" -> FloatArbitrary()
            "java.lang.String", "kotlin.String" -> StringArbitrary()
            "java.math.BigInteger" -> BigIntegerArbitrary()
            "java.math.BigDecimal" -> BigDecimalArbitrary()
            else -> forArbitraryKClass(Class.forName(className).kotlin, usedClasses, recursionDeep)
        }

        fun <T : Any> forArbitraryKClass(
            klass: KClass<T>,
            usedClasses: Set<KClass<*>>,
            recursionDepth: Int = 0
        ): Arbitrary<T> {
            fun <T> forConstructor(constructor: KFunction<T>): Arbitrary<T>? {
                val paramArbs = constructor.parameters
                    .map { it.type }
                    .map { it.withNullability(false) }
                    .map { it.jvmErasure }
                    .map {
                        val name = it.qualifiedName ?: return null
                        if (recursionDepth < DEFAULT_MAX_RECURSION_DEPTH || it !in usedClasses) {
                            forClassName(name, usedClasses + it, recursionDepth + 1)
                        } else {
                            return null
                        }
                    }

                return Arbitrary(object : Gen<T> {
                    override fun generate(): T {
                        val args = paramArbs.map { it.generate() }.toTypedArray()
                        return constructor.call(*args)
                    }
                })
            }

            val arbs = klass.constructors.map { forConstructor(it) }.filterNotNull()
            return when (arbs.size) {
                0 -> throw IllegalArgumentException("Cannot create arbitrary for ${klass.qualifiedName}")
                1 -> arbs.first()
                else -> OneOfGenArbitrary(arbs)
            }
        }
    }
}
