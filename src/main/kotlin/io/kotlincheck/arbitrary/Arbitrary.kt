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
        val shrinker: Shrinker<T> = DummyShrinker<T>()
) : Gen<T> by gen, Shrinker<T> by shrinker {

    @Suppress("UNCHECKED_CAST")
    companion object {
        val DEFAULT_CONTAINER_SIZE_BOUND = 100
        val DEFAULT_MAX_RECURSION_DEPTH = 5

        inline fun <reified T> default(): Arbitrary<T> = when {
            Collection::class.isSuperclassOf(T::class) -> {
                val type = TypeParameterProvider.getNthTypeParameter<T>(0)
                val elemArb = forClassName(type.typeName)
                requireNotNull(elemArb) { "Cannot infer arbitrary for ${T::class.qualifiedName}" }
                when (T::class) {
                    List::class -> ListArbitrary(elemArb!!) as Arbitrary<T>
                    MutableList::class -> MutableListArbitrary(elemArb!!) as Arbitrary<T>
                    Set::class -> SetArbitrary(elemArb!!) as Arbitrary<T>
                    MutableSet::class -> MutableSetArbitrary(elemArb!!) as Arbitrary<T>
                    else -> throw IllegalArgumentException("Cannot infer arbitrary for " +
                                                           "${T::class.qualifiedName}")
                }
            }
            Map::class.isSuperclassOf(T::class) -> {
                val keyType = TypeParameterProvider.getNthTypeParameter<T>(0)
                val keyArb = forClassName(keyType.typeName)
                requireNotNull(keyArb) { "Cannot infer arbitrary for ${T::class.qualifiedName}" }
                val valueType = TypeParameterProvider.getNthTypeParameter<T>(1)
                val valueArb = forClassName(valueType.typeName)
                requireNotNull(valueArb) { "Cannot infer arbitrary for ${T::class.qualifiedName}" }
                when (T::class) {
                    Map::class -> MapArbitrary(keyArb!!, valueArb!!) as Arbitrary<T>
                    MutableMap::class -> MutableMapArbitrary(keyArb!!, valueArb!!) as Arbitrary<T>
                    else -> throw IllegalArgumentException("Cannot infer arbitrary for " +
                                                           "${T::class.qualifiedName}")
                }
            }
            else -> forClassName(T::class.qualifiedName!!) as Arbitrary<T>
        }

        fun forClassName(
                className: String,
                usedClasses: Set<KClass<*>> = setOf(),
                recursionDeep: Int = 0
        ): Arbitrary<*>? = when (className) {
            "java.lang.Long", "kotlin.Long" -> LongArbitrary()
            "java.lang.Integer", "kotlin.Int" -> IntArbitrary()
            "java.lang.Short", "kotlin.Short" -> ShortArbitrary()
            "java.lang.Byte", "kotlin.Byte" -> ByteArbitrary()
            "java.lang.Char", "kotlin.Char" -> CharArbitrary()
            "java.lang.Boolean", "kotlin.Boolean" -> BooleanArbitrary()
            "java.lang.Double", "kotlin.Double" -> DoubleArbitrary()
            "java.lang.Float", "kotlin.Float" -> FloatArbitrary()
            "java.lang.String", "kotlin.String" -> StringArbitrary()
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
                            if (recursionDepth < DEFAULT_MAX_RECURSION_DEPTH || it !in usedClasses) {
                                forClassName(it.qualifiedName!!, usedClasses + it, recursionDepth + 1)
                            } else {
                                null
                            }
                        }
                if (paramArbs.contains(null)) {
                    return null
                }

                return Arbitrary(object : Gen<T> {
                    override fun generate(): T {
                        val args = paramArbs.map { it!!.generate() }.toTypedArray()
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
