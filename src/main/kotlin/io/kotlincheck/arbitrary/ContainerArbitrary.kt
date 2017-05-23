package io.kotlincheck.arbitrary

import io.kotlincheck.gen.MutableListGen
import io.kotlincheck.gen.MutableMapGen
import io.kotlincheck.gen.MutableSetGen
import io.kotlincheck.gen.StringGen
import io.kotlincheck.shrink.DummyShrinker
import io.kotlincheck.shrink.MutableListShrinker
import io.kotlincheck.shrink.StringShrinker

class ListArbitrary<T>(
        arb: Arbitrary<T>,
        sizeBound: Int = Arbitrary.DEFAULT_CONTAINER_SIZE_BOUND,
        fixedSize: Boolean = false
) : Arbitrary<MutableList<T>>(
        MutableListGen(arb, sizeBound, fixedSize),
        MutableListShrinker(arb, sizeBound, fixedSize)
)

class MutableListArbitrary<T>(
        arb: Arbitrary<T>,
        sizeBound: Int = Arbitrary.DEFAULT_CONTAINER_SIZE_BOUND,
        fixedSize: Boolean = false
) : Arbitrary<MutableList<T>>(
        MutableListGen(arb, sizeBound, fixedSize),
        MutableListShrinker(arb, sizeBound, fixedSize)
)

class SetArbitrary<T>(
        arb: Arbitrary<T>,
        sizeBound: Int = Arbitrary.DEFAULT_CONTAINER_SIZE_BOUND,
        fixedSize: Boolean = false
) : Arbitrary<Set<T>>(
        MutableSetGen(arb, sizeBound, fixedSize),
        DummyShrinker()
)

class MutableSetArbitrary<T>(
        arb: Arbitrary<T>,
        sizeBound: Int = Arbitrary.DEFAULT_CONTAINER_SIZE_BOUND,
        fixedSize: Boolean = false
) : Arbitrary<MutableSet<T>>(
        MutableSetGen(arb, sizeBound, fixedSize),
        DummyShrinker()
)

class MapArbitrary<K, V>(
        keyArb: Arbitrary<K>,
        valueArb: Arbitrary<V>,
        sizeBound: Int = Arbitrary.DEFAULT_CONTAINER_SIZE_BOUND,
        fixedSize: Boolean = false
) : Arbitrary<Map<K, V>>(
        MutableMapGen(keyArb, valueArb, sizeBound, fixedSize),
        DummyShrinker()
)

class MutableMapArbitrary<K, V>(
        keyArb: Arbitrary<K>,
        valueArb: Arbitrary<V>,
        sizeBound: Int = Arbitrary.DEFAULT_CONTAINER_SIZE_BOUND,
        fixedSize: Boolean = false
) : Arbitrary<MutableMap<K, V>>(
        MutableMapGen(keyArb, valueArb, sizeBound, fixedSize),
        DummyShrinker()
)

class StringArbitrary(
        sizeBound: Int = Arbitrary.DEFAULT_CONTAINER_SIZE_BOUND,
        fixedSize: Boolean = false
) : Arbitrary<String>(
        StringGen(sizeBound, fixedSize),
        StringShrinker(sizeBound, fixedSize)
)
