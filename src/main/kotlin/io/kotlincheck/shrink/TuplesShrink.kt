package io.kotlincheck.shrink

import io.kotlincheck.*

class PairShrinker<A, B>(
    val shrinker1: Shrinker<A>,
    val shrinker2: Shrinker<B>
) : Shrinker<Pair<A, B>> {
    override fun shrink(
        test: (Pair<A, B>) -> Boolean,
        counterexample: Pair<A, B>
    ): Pair<A, B> {
        var (first, second) = counterexample
        first = shrinker1.shrink({ test(Pair(it, second)) }, first)
        second = shrinker2.shrink({ test(Pair(first, it)) }, second)
        return Pair(first, second)
    }
}

class TripleShrinker<A, B, C>(
    val shrinker1: Shrinker<A>,
    val shrinker2: Shrinker<B>,
    val shrinker3: Shrinker<C>
) : Shrinker<Triple<A, B, C>> {
    override fun shrink(
        test: (Triple<A, B, C>) -> Boolean,
        counterexample: Triple<A, B, C>
    ): Triple<A, B, C> {
        var (first, second, third) = counterexample
        first = shrinker1.shrink({ test(Triple(it, second, third)) }, first)
        second = shrinker2.shrink({ test(Triple(first, it, third)) }, second)
        third = shrinker3.shrink({ test(Triple(first, second, it)) }, third)
        return Triple(first, second, third)
    }
}

internal class Tuple2Shrinker<T1, T2>(
    val shrinker1: Shrinker<T1>,
    val shrinker2: Shrinker<T2>
) : Shrinker<Tuple2<T1, T2>> {
    override fun shrink(
        test: (Tuple2<T1, T2>) -> Boolean,
        counterexample: Tuple2<T1, T2>
    ): Tuple2<T1, T2> {
        var (elem1, elem2) = counterexample
        elem1 = shrinker1.shrink({ test(Tuple2(it, elem2)) }, elem1)
        elem2 = shrinker2.shrink({ test(Tuple2(elem1, it)) }, elem2)
        return Tuple2(elem1, elem2)
    }
}

internal class Tuple3Shrinker<T1, T2, T3>(
    val shrinker1: Shrinker<T1>,
    val shrinker2: Shrinker<T2>,
    val shrinker3: Shrinker<T3>
) : Shrinker<Tuple3<T1, T2, T3>> {
    override fun shrink(
        test: (Tuple3<T1, T2, T3>) -> Boolean,
        counterexample: Tuple3<T1, T2, T3>
    ): Tuple3<T1, T2, T3> {
        var (elem1, elem2, elem3) = counterexample
        elem1 = shrinker1.shrink({ test(Tuple3(it, elem2, elem3)) }, elem1)
        elem2 = shrinker2.shrink({ test(Tuple3(elem1, it, elem3)) }, elem2)
        elem3 = shrinker3.shrink({ test(Tuple3(elem1, elem2, it)) }, elem3)
        return Tuple3(elem1, elem2, elem3)
    }
}

internal class Tuple4Shrinker<T1, T2, T3, T4>(
    val shrinker1: Shrinker<T1>,
    val shrinker2: Shrinker<T2>,
    val shrinker3: Shrinker<T3>,
    val shrinker4: Shrinker<T4>
) : Shrinker<Tuple4<T1, T2, T3, T4>> {
    override fun shrink(
        test: (Tuple4<T1, T2, T3, T4>) -> Boolean,
        counterexample: Tuple4<T1, T2, T3, T4>
    ): Tuple4<T1, T2, T3, T4> {
        var (elem1, elem2, elem3, elem4) = counterexample
        elem1 = shrinker1.shrink({ test(Tuple4(it, elem2, elem3, elem4)) }, elem1)
        elem2 = shrinker2.shrink({ test(Tuple4(elem1, it, elem3, elem4)) }, elem2)
        elem3 = shrinker3.shrink({ test(Tuple4(elem1, elem2, it, elem4)) }, elem3)
        elem4 = shrinker4.shrink({ test(Tuple4(elem1, elem2, elem3, it)) }, elem4)
        return Tuple4(elem1, elem2, elem3, elem4)
    }
}

internal class Tuple5Shrinker<T1, T2, T3, T4, T5>(
    val shrinker1: Shrinker<T1>,
    val shrinker2: Shrinker<T2>,
    val shrinker3: Shrinker<T3>,
    val shrinker4: Shrinker<T4>,
    val shrinker5: Shrinker<T5>
) : Shrinker<Tuple5<T1, T2, T3, T4, T5>> {
    override fun shrink(
        test: (Tuple5<T1, T2, T3, T4, T5>) -> Boolean,
        counterexample: Tuple5<T1, T2, T3, T4, T5>
    ): Tuple5<T1, T2, T3, T4, T5> {
        var (elem1, elem2, elem3, elem4, elem5) = counterexample
        elem1 = shrinker1.shrink({ test(Tuple5(it, elem2, elem3, elem4, elem5)) }, elem1)
        elem2 = shrinker2.shrink({ test(Tuple5(elem1, it, elem3, elem4, elem5)) }, elem2)
        elem3 = shrinker3.shrink({ test(Tuple5(elem1, elem2, it, elem4, elem5)) }, elem3)
        elem4 = shrinker4.shrink({ test(Tuple5(elem1, elem2, elem3, it, elem5)) }, elem4)
        elem5 = shrinker5.shrink({ test(Tuple5(elem1, elem2, elem3, elem4, it)) }, elem5)
        return Tuple5(elem1, elem2, elem3, elem4, elem5)
    }
}

internal class Tuple6Shrinker<T1, T2, T3, T4, T5, T6>(
    val shrinker1: Shrinker<T1>,
    val shrinker2: Shrinker<T2>,
    val shrinker3: Shrinker<T3>,
    val shrinker4: Shrinker<T4>,
    val shrinker5: Shrinker<T5>,
    val shrinker6: Shrinker<T6>
) : Shrinker<Tuple6<T1, T2, T3, T4, T5, T6>> {
    override fun shrink(
        test: (Tuple6<T1, T2, T3, T4, T5, T6>) -> Boolean,
        counterexample: Tuple6<T1, T2, T3, T4, T5, T6>
    ): Tuple6<T1, T2, T3, T4, T5, T6> {
        var (elem1, elem2, elem3, elem4, elem5, elem6) = counterexample
        elem1 = shrinker1.shrink({ test(Tuple6(it, elem2, elem3, elem4, elem5, elem6)) }, elem1)
        elem2 = shrinker2.shrink({ test(Tuple6(elem1, it, elem3, elem4, elem5, elem6)) }, elem2)
        elem3 = shrinker3.shrink({ test(Tuple6(elem1, elem2, it, elem4, elem5, elem6)) }, elem3)
        elem4 = shrinker4.shrink({ test(Tuple6(elem1, elem2, elem3, it, elem5, elem6)) }, elem4)
        elem5 = shrinker5.shrink({ test(Tuple6(elem1, elem2, elem3, elem4, it, elem6)) }, elem5)
        elem6 = shrinker6.shrink({ test(Tuple6(elem1, elem2, elem3, elem4, elem5, it)) }, elem6)
        return Tuple6(elem1, elem2, elem3, elem4, elem5, elem6)
    }
}

internal class Tuple7Shrinker<T1, T2, T3, T4, T5, T6, T7>(
    val shrinker1: Shrinker<T1>,
    val shrinker2: Shrinker<T2>,
    val shrinker3: Shrinker<T3>,
    val shrinker4: Shrinker<T4>,
    val shrinker5: Shrinker<T5>,
    val shrinker6: Shrinker<T6>,
    val shrinker7: Shrinker<T7>
) : Shrinker<Tuple7<T1, T2, T3, T4, T5, T6, T7>> {
    override fun shrink(
        test: (Tuple7<T1, T2, T3, T4, T5, T6, T7>) -> Boolean,
        counterexample: Tuple7<T1, T2, T3, T4, T5, T6, T7>
    ): Tuple7<T1, T2, T3, T4, T5, T6, T7> {
        var (elem1, elem2, elem3, elem4, elem5, elem6, elem7) = counterexample
        elem1 = shrinker1.shrink({ test(Tuple7(it, elem2, elem3, elem4, elem5, elem6, elem7)) }, elem1)
        elem2 = shrinker2.shrink({ test(Tuple7(elem1, it, elem3, elem4, elem5, elem6, elem7)) }, elem2)
        elem3 = shrinker3.shrink({ test(Tuple7(elem1, elem2, it, elem4, elem5, elem6, elem7)) }, elem3)
        elem4 = shrinker4.shrink({ test(Tuple7(elem1, elem2, elem3, it, elem5, elem6, elem7)) }, elem4)
        elem5 = shrinker5.shrink({ test(Tuple7(elem1, elem2, elem3, elem4, it, elem6, elem7)) }, elem5)
        elem6 = shrinker6.shrink({ test(Tuple7(elem1, elem2, elem3, elem4, elem5, it, elem7)) }, elem6)
        elem7 = shrinker7.shrink({ test(Tuple7(elem1, elem2, elem3, elem4, elem5, elem6, it)) }, elem7)
        return Tuple7(elem1, elem2, elem3, elem4, elem5, elem6, elem7)
    }
}

internal class Tuple8Shrinker<T1, T2, T3, T4, T5, T6, T7, T8>(
    val shrinker1: Shrinker<T1>,
    val shrinker2: Shrinker<T2>,
    val shrinker3: Shrinker<T3>,
    val shrinker4: Shrinker<T4>,
    val shrinker5: Shrinker<T5>,
    val shrinker6: Shrinker<T6>,
    val shrinker7: Shrinker<T7>,
    val shrinker8: Shrinker<T8>
) : Shrinker<Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>> {
    override fun shrink(
        test: (Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>) -> Boolean,
        counterexample: Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>
    ): Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> {
        var (elem1, elem2, elem3, elem4, elem5, elem6, elem7, elem8) = counterexample
        elem1 = shrinker1.shrink({ test(Tuple8(it, elem2, elem3, elem4, elem5, elem6, elem7, elem8)) }, elem1)
        elem2 = shrinker2.shrink({ test(Tuple8(elem1, it, elem3, elem4, elem5, elem6, elem7, elem8)) }, elem2)
        elem3 = shrinker3.shrink({ test(Tuple8(elem1, elem2, it, elem4, elem5, elem6, elem7, elem8)) }, elem3)
        elem4 = shrinker4.shrink({ test(Tuple8(elem1, elem2, elem3, it, elem5, elem6, elem7, elem8)) }, elem4)
        elem5 = shrinker5.shrink({ test(Tuple8(elem1, elem2, elem3, elem4, it, elem6, elem7, elem8)) }, elem5)
        elem6 = shrinker6.shrink({ test(Tuple8(elem1, elem2, elem3, elem4, elem5, it, elem7, elem8)) }, elem6)
        elem7 = shrinker7.shrink({ test(Tuple8(elem1, elem2, elem3, elem4, elem5, elem6, it, elem8)) }, elem7)
        elem8 = shrinker8.shrink({ test(Tuple8(elem1, elem2, elem3, elem4, elem5, elem6, elem7, it)) }, elem8)
        return Tuple8(elem1, elem2, elem3, elem4, elem5, elem6, elem7, elem8)
    }
}

internal class Tuple9Shrinker<T1, T2, T3, T4, T5, T6, T7, T8, T9>(
    val shrinker1: Shrinker<T1>,
    val shrinker2: Shrinker<T2>,
    val shrinker3: Shrinker<T3>,
    val shrinker4: Shrinker<T4>,
    val shrinker5: Shrinker<T5>,
    val shrinker6: Shrinker<T6>,
    val shrinker7: Shrinker<T7>,
    val shrinker8: Shrinker<T8>,
    val shrinker9: Shrinker<T9>
) : Shrinker<Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>> {
    override fun shrink(
        test: (Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>) -> Boolean,
        counterexample: Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>
    ): Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> {
        var (elem1, elem2, elem3, elem4, elem5, elem6, elem7, elem8, elem9) = counterexample
        elem1 = shrinker1.shrink(
            { test(Tuple9(it, elem2, elem3, elem4, elem5, elem6, elem7, elem8, elem9)) },
            elem1
        )
        elem2 = shrinker2.shrink(
            { test(Tuple9(elem1, it, elem3, elem4, elem5, elem6, elem7, elem8, elem9)) },
            elem2
        )
        elem3 = shrinker3.shrink(
            { test(Tuple9(elem1, elem2, it, elem4, elem5, elem6, elem7, elem8, elem9)) },
            elem3
        )
        elem4 = shrinker4.shrink(
            { test(Tuple9(elem1, elem2, elem3, it, elem5, elem6, elem7, elem8, elem9)) },
            elem4
        )
        elem5 = shrinker5.shrink(
            { test(Tuple9(elem1, elem2, elem3, elem4, it, elem6, elem7, elem8, elem9)) },
            elem5
        )
        elem6 = shrinker6.shrink(
            { test(Tuple9(elem1, elem2, elem3, elem4, elem5, it, elem7, elem8, elem9)) },
            elem6
        )
        elem7 = shrinker7.shrink(
            { test(Tuple9(elem1, elem2, elem3, elem4, elem5, elem6, it, elem8, elem9)) },
            elem7
        )
        elem8 = shrinker8.shrink(
            { test(Tuple9(elem1, elem2, elem3, elem4, elem5, elem6, elem7, it, elem9)) },
            elem8
        )
        elem9 = shrinker9.shrink(
            { test(Tuple9(elem1, elem2, elem3, elem4, elem5, elem6, elem7, elem8, it)) },
            elem9
        )
        return Tuple9(elem1, elem2, elem3, elem4, elem5, elem6, elem7, elem8, elem9)
    }
}
