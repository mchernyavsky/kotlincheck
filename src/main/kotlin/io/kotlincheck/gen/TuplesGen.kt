package io.kotlincheck.gen

import io.kotlincheck.*

class PairGen<out A, out B>(val gen1: Gen<A>, val gen2: Gen<B>
) : Gen<Pair<A, B>> {
    override fun generate(): Pair<A, B> = Pair(gen1.generate(), gen2.generate())
}

class TripleGen<out A, out B, out C>(val gen1: Gen<A>, val gen2: Gen<B>, val gen3: Gen<C>
) : Gen<Triple<A, B, C>> {
    override fun generate(): Triple<A, B, C> = Triple(gen1.generate(), gen2.generate(), gen3.generate())
}

internal class Tuple2Gen<out T1, out T2>(
        val gen1: Gen<T1>,
        val gen2: Gen<T2>
) : Gen<Tuple2<T1, T2>> {
    override fun generate(): Tuple2<T1, T2> = Tuple2(gen1.generate(), gen2.generate())
}

internal class Tuple3Gen<out T1, out T2, out T3>(
        val gen1: Gen<T1>,
        val gen2: Gen<T2>,
        val gen3: Gen<T3>
) : Gen<Tuple3<T1, T2, T3>> {
    override fun generate(): Tuple3<T1, T2, T3> = Tuple3(gen1.generate(), gen2.generate(), gen3.generate())
}

internal class Tuple4Gen<out T1, out T2, out T3, out T4>(
        val gen1: Gen<T1>,
        val gen2: Gen<T2>,
        val gen3: Gen<T3>,
        val gen4: Gen<T4>
) : Gen<Tuple4<T1, T2, T3, T4>> {
    override fun generate(): Tuple4<T1, T2, T3, T4> =
            Tuple4(gen1.generate(), gen2.generate(), gen3.generate(),
                   gen4.generate())
}

internal class Tuple5Gen<out T1, out T2, out T3, out T4, out T5>(
        val gen1: Gen<T1>,
        val gen2: Gen<T2>,
        val gen3: Gen<T3>,
        val gen4: Gen<T4>,
        val gen5: Gen<T5>
) : Gen<Tuple5<T1, T2, T3, T4, T5>> {
    override fun generate(): Tuple5<T1, T2, T3, T4, T5> =
            Tuple5(gen1.generate(), gen2.generate(), gen3.generate(),
                   gen4.generate(), gen5.generate())
}

internal class Tuple6Gen<out T1, out T2, out T3, out T4, out T5, out T6>(
        val gen1: Gen<T1>,
        val gen2: Gen<T2>,
        val gen3: Gen<T3>,
        val gen4: Gen<T4>,
        val gen5: Gen<T5>,
        val gen6: Gen<T6>
) : Gen<Tuple6<T1, T2, T3, T4, T5, T6>> {
    override fun generate(): Tuple6<T1, T2, T3, T4, T5, T6> =
            Tuple6(gen1.generate(), gen2.generate(), gen3.generate(),
                   gen4.generate(), gen5.generate(), gen6.generate())
}

internal class Tuple7Gen<out T1, out T2, out T3, out T4, out T5, out T6, out T7>(
        val gen1: Gen<T1>,
        val gen2: Gen<T2>,
        val gen3: Gen<T3>,
        val gen4: Gen<T4>,
        val gen5: Gen<T5>,
        val gen6: Gen<T6>,
        val gen7: Gen<T7>
) : Gen<Tuple7<T1, T2, T3, T4, T5, T6, T7>> {
    override fun generate(): Tuple7<T1, T2, T3, T4, T5, T6, T7> =
            Tuple7(gen1.generate(), gen2.generate(), gen3.generate(),
                   gen4.generate(), gen5.generate(), gen6.generate(),
                   gen7.generate())
}

internal class Tuple8Gen<out T1, out T2, out T3, out T4, out T5, out T6, out T7, out T8>(
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
            Tuple8(gen1.generate(), gen2.generate(), gen3.generate(),
                   gen4.generate(), gen5.generate(), gen6.generate(),
                   gen7.generate(), gen8.generate())
}

internal class Tuple9Gen<out T1, out T2, out T3, out T4, out T5, out T6, out T7, out T8, out T9>(
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
            Tuple9(gen1.generate(), gen2.generate(), gen3.generate(),
                   gen4.generate(), gen5.generate(), gen6.generate(),
                   gen7.generate(), gen8.generate(), gen9.generate())
}
