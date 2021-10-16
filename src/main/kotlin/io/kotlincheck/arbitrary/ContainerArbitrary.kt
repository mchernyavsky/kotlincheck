package io.kotlincheck.arbitrary

import io.kotlincheck.gen.MutableListGen
import io.kotlincheck.gen.MutableMapGen
import io.kotlincheck.gen.MutableSetGen
import io.kotlincheck.gen.StringGen
import io.kotlincheck.shrink.MutableListShrinker
import io.kotlincheck.shrink.StringShrinker

class MutableListArbitrary<T>(
    arb: Arbitrary<T>,
    sizeBound: Int = DEFAULT_CONTAINER_SIZE_BOUND,
    fixedSize: Boolean = false
) : Arbitrary<MutableList<T>>(
    MutableListGen(arb, sizeBound, fixedSize),
    MutableListShrinker(arb, sizeBound, fixedSize)
)

class MutableSetArbitrary<T>(
    arb: Arbitrary<T>,
    sizeBound: Int = DEFAULT_CONTAINER_SIZE_BOUND,
    fixedSize: Boolean = false
) : Arbitrary<MutableSet<T>>(MutableSetGen(arb, sizeBound, fixedSize))

class MutableMapArbitrary<K, V>(
    keyArb: Arbitrary<K>,
    valueArb: Arbitrary<V>,
    sizeBound: Int = DEFAULT_CONTAINER_SIZE_BOUND,
    fixedSize: Boolean = false
) : Arbitrary<MutableMap<K, V>>(MutableMapGen(keyArb, valueArb, sizeBound, fixedSize))

class StringArbitrary(
    sizeBound: Int = DEFAULT_CONTAINER_SIZE_BOUND,
    fixedSize: Boolean = false
) : Arbitrary<String>(
    StringGen(sizeBound, fixedSize),
    StringShrinker(sizeBound, fixedSize)
)

/** API */

inline fun <reified T> lists(
    values: Arbitrary<T> = Arbitrary.default(),
    sizeBound: Int = Arbitrary.DEFAULT_CONTAINER_SIZE_BOUND,
    fixedSize: Boolean = false
): MutableListArbitrary<T> = MutableListArbitrary(values, sizeBound = sizeBound, fixedSize = fixedSize)

inline fun <reified T> sets(
    values: Arbitrary<T> = Arbitrary.default(),
    sizeBound: Int = Arbitrary.DEFAULT_CONTAINER_SIZE_BOUND,
    fixedSize: Boolean = false
): MutableSetArbitrary<T> = MutableSetArbitrary(values, sizeBound = sizeBound, fixedSize = fixedSize)

inline fun <reified K, reified V> maps(
    keys: Arbitrary<K> = Arbitrary.default(),
    values: Arbitrary<V> = Arbitrary.default(),
    sizeBound: Int = Arbitrary.DEFAULT_CONTAINER_SIZE_BOUND,
    fixedSize: Boolean = false
): MutableMapArbitrary<K, V> = MutableMapArbitrary(keys, values, sizeBound = sizeBound, fixedSize = fixedSize)

fun strings(
    sizeBound: Int = Arbitrary.DEFAULT_CONTAINER_SIZE_BOUND,
    fixedSize: Boolean = false
): StringArbitrary = StringArbitrary(sizeBound = sizeBound, fixedSize = fixedSize)
