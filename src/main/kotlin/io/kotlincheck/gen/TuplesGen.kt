package io.kotlincheck.gen

import io.kotlincheck.*

class PairGen<A, B>(
    val gen1: Gen<A>, val gen2: Gen<B>
) : Gen<Pair<A, B>> {

    override fun generate(): Pair<A, B> = Pair(gen1.generate(), gen2.generate())

    override fun isAcceptable(value: Pair<A, B>): Boolean =
        gen1.isAcceptable(value.first) && gen2.isAcceptable(value.second)
}

class TripleGen<A, B, C>(
    val gen1: Gen<A>, val gen2: Gen<B>, val gen3: Gen<C>
) : Gen<Triple<A, B, C>> {
    override fun generate(): Triple<A, B, C> = Triple(gen1.generate(), gen2.generate(), gen3.generate())

    override fun isAcceptable(value: Triple<A, B, C>): Boolean =
        gen1.isAcceptable(value.first) && gen2.isAcceptable(value.second) && gen3.isAcceptable(value.third)
}

internal class Tuple2Gen<T1, T2>(
    val gen1: Gen<T1>,
    val gen2: Gen<T2>
) : Gen<Tuple2<T1, T2>> {

    override fun generate(): Tuple2<T1, T2> = Tuple2(gen1.generate(), gen2.generate())

    override fun isAcceptable(value: Tuple2<T1, T2>): Boolean =
        gen1.isAcceptable(value.elem1) && gen2.isAcceptable(value.elem2)
}

internal class Tuple3Gen<T1, T2, T3>(
    val gen1: Gen<T1>,
    val gen2: Gen<T2>,
    val gen3: Gen<T3>
) : Gen<Tuple3<T1, T2, T3>> {

    override fun generate(): Tuple3<T1, T2, T3> = Tuple3(gen1.generate(), gen2.generate(), gen3.generate())

    override fun isAcceptable(value: Tuple3<T1, T2, T3>): Boolean =
        gen1.isAcceptable(value.elem1) && gen2.isAcceptable(value.elem2) && gen3.isAcceptable(value.elem3)
}

internal class Tuple4Gen<T1, T2, T3, T4>(
    val gen1: Gen<T1>,
    val gen2: Gen<T2>,
    val gen3: Gen<T3>,
    val gen4: Gen<T4>
) : Gen<Tuple4<T1, T2, T3, T4>> {

    override fun generate(): Tuple4<T1, T2, T3, T4> =
        Tuple4(
            gen1.generate(), gen2.generate(), gen3.generate(),
            gen4.generate()
        )

    override fun isAcceptable(value: Tuple4<T1, T2, T3, T4>): Boolean =
        gen1.isAcceptable(value.elem1) && gen2.isAcceptable(value.elem2) && gen3.isAcceptable(value.elem3) &&
                gen4.isAcceptable(value.elem4)
}

internal class Tuple5Gen<T1, T2, T3, T4, T5>(
    val gen1: Gen<T1>,
    val gen2: Gen<T2>,
    val gen3: Gen<T3>,
    val gen4: Gen<T4>,
    val gen5: Gen<T5>
) : Gen<Tuple5<T1, T2, T3, T4, T5>> {

    override fun generate(): Tuple5<T1, T2, T3, T4, T5> =
        Tuple5(
            gen1.generate(), gen2.generate(), gen3.generate(),
            gen4.generate(), gen5.generate()
        )

    override fun isAcceptable(value: Tuple5<T1, T2, T3, T4, T5>): Boolean =
        gen1.isAcceptable(value.elem1) && gen2.isAcceptable(value.elem2) && gen3.isAcceptable(value.elem3) &&
                gen4.isAcceptable(value.elem4) && gen5.isAcceptable(value.elem5)
}

internal class Tuple6Gen<T1, T2, T3, T4, T5, T6>(
    val gen1: Gen<T1>,
    val gen2: Gen<T2>,
    val gen3: Gen<T3>,
    val gen4: Gen<T4>,
    val gen5: Gen<T5>,
    val gen6: Gen<T6>
) : Gen<Tuple6<T1, T2, T3, T4, T5, T6>> {

    override fun generate(): Tuple6<T1, T2, T3, T4, T5, T6> =
        Tuple6(
            gen1.generate(), gen2.generate(), gen3.generate(),
            gen4.generate(), gen5.generate(), gen6.generate()
        )

    override fun isAcceptable(value: Tuple6<T1, T2, T3, T4, T5, T6>): Boolean =
        gen1.isAcceptable(value.elem1) && gen2.isAcceptable(value.elem2) && gen3.isAcceptable(value.elem3) &&
                gen4.isAcceptable(value.elem4) && gen5.isAcceptable(value.elem5) && gen6.isAcceptable(value.elem6)
}

internal class Tuple7Gen<T1, T2, T3, T4, T5, T6, T7>(
    val gen1: Gen<T1>,
    val gen2: Gen<T2>,
    val gen3: Gen<T3>,
    val gen4: Gen<T4>,
    val gen5: Gen<T5>,
    val gen6: Gen<T6>,
    val gen7: Gen<T7>
) : Gen<Tuple7<T1, T2, T3, T4, T5, T6, T7>> {

    override fun generate(): Tuple7<T1, T2, T3, T4, T5, T6, T7> =
        Tuple7(
            gen1.generate(), gen2.generate(), gen3.generate(),
            gen4.generate(), gen5.generate(), gen6.generate(),
            gen7.generate()
        )

    override fun isAcceptable(value: Tuple7<T1, T2, T3, T4, T5, T6, T7>): Boolean =
        gen1.isAcceptable(value.elem1) && gen2.isAcceptable(value.elem2) && gen3.isAcceptable(value.elem3)
                && gen4.isAcceptable(value.elem4) && gen5.isAcceptable(value.elem5) && gen6.isAcceptable(value.elem6)
                && gen7.isAcceptable(value.elem7)
}

internal class Tuple8Gen<T1, T2, T3, T4, T5, T6, T7, T8>(
    val gen1: Gen<T1>,
    val gen2: Gen<T2>,
    val gen3: Gen<T3>,
    val gen4: Gen<T4>,
    val gen5: Gen<T5>,
    val gen6: Gen<T6>,
    val gen7: Gen<T7>,
    val gen8: Gen<T8>
) : Gen<Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>> {

    override fun generate(): Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> =
        Tuple8(
            gen1.generate(), gen2.generate(), gen3.generate(),
            gen4.generate(), gen5.generate(), gen6.generate(),
            gen7.generate(), gen8.generate()
        )

    override fun isAcceptable(value: Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>): Boolean =
        gen1.isAcceptable(value.elem1) && gen2.isAcceptable(value.elem2) && gen3.isAcceptable(value.elem3)
                && gen4.isAcceptable(value.elem4) && gen5.isAcceptable(value.elem5) && gen6.isAcceptable(value.elem6)
                && gen7.isAcceptable(value.elem7) && gen8.isAcceptable(value.elem8)
}

internal class Tuple9Gen<T1, T2, T3, T4, T5, T6, T7, T8, T9>(
    val gen1: Gen<T1>,
    val gen2: Gen<T2>,
    val gen3: Gen<T3>,
    val gen4: Gen<T4>,
    val gen5: Gen<T5>,
    val gen6: Gen<T6>,
    val gen7: Gen<T7>,
    val gen8: Gen<T8>,
    val gen9: Gen<T9>
) : Gen<Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>> {

    override fun generate(): Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> =
        Tuple9(
            gen1.generate(), gen2.generate(), gen3.generate(),
            gen4.generate(), gen5.generate(), gen6.generate(),
            gen7.generate(), gen8.generate(), gen9.generate()
        )

    override fun isAcceptable(value: Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>): Boolean =
        gen1.isAcceptable(value.elem1) && gen2.isAcceptable(value.elem2) && gen3.isAcceptable(value.elem3)
                && gen4.isAcceptable(value.elem4) && gen5.isAcceptable(value.elem5) && gen6.isAcceptable(value.elem6)
                && gen7.isAcceptable(value.elem7) && gen8.isAcceptable(value.elem8) && gen9.isAcceptable(value.elem9)
}
