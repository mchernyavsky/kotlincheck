package io.kotlincheck.arbitrary

import io.kotlincheck.*
import io.kotlincheck.gen.*
import io.kotlincheck.shrink.*

class PairArbitary<A, B>(
        arb1: Arbitrary<A>,
        arb2: Arbitrary<B>
) : Arbitrary<Pair<A, B>>(
        PairGen(arb1, arb2),
        PairShrinker(arb1, arb2)
)

class TripleArbitary<A, B, C>(
        arb1: Arbitrary<A>,
        arb2: Arbitrary<B>,
        arb3: Arbitrary<C>
) : Arbitrary<Triple<A, B, C>>(
        TripleGen(arb1, arb2, arb3),
        TripleShrinker(arb1, arb2, arb3)
)

internal class Tuple2Arbitary<T1, T2>(
        arb1: Arbitrary<T1>,
        arb2: Arbitrary<T2>
) : Arbitrary<Tuple2<T1, T2>>(
        Tuple2Gen(arb1, arb2),
        Tuple2Shrinker(arb1, arb2)
)

internal class Tuple3Arbitary<T1, T2, T3>(
        arb1: Arbitrary<T1>,
        arb2: Arbitrary<T2>,
        arb3: Arbitrary<T3>
) : Arbitrary<Tuple3<T1, T2, T3>>(
        Tuple3Gen(arb1, arb2, arb3),
        Tuple3Shrinker(arb1, arb2, arb3)
)

internal class Tuple4Arbitary<T1, T2, T3, T4>(
        arb1: Arbitrary<T1>,
        arb2: Arbitrary<T2>,
        arb3: Arbitrary<T3>,
        arb4: Arbitrary<T4>
) : Arbitrary<Tuple4<T1, T2, T3, T4>>(
        Tuple4Gen(arb1, arb2, arb3, arb4),
        Tuple4Shrinker(arb1, arb2, arb3, arb4)
)

internal class Tuple5Arbitary<T1, T2, T3, T4, T5>(
        arb1: Arbitrary<T1>,
        arb2: Arbitrary<T2>,
        arb3: Arbitrary<T3>,
        arb4: Arbitrary<T4>,
        arb5: Arbitrary<T5>
) : Arbitrary<Tuple5<T1, T2, T3, T4, T5>>(
        Tuple5Gen(arb1, arb2, arb3, arb4, arb5),
        Tuple5Shrinker(arb1, arb2, arb3, arb4, arb5)
)

internal class Tuple6Arbitary<T1, T2, T3, T4, T5, T6>(
        arb1: Arbitrary<T1>,
        arb2: Arbitrary<T2>,
        arb3: Arbitrary<T3>,
        arb4: Arbitrary<T4>,
        arb5: Arbitrary<T5>,
        arb6: Arbitrary<T6>
) : Arbitrary<Tuple6<T1, T2, T3, T4, T5, T6>>(
        Tuple6Gen(arb1, arb2, arb3, arb4, arb5, arb6),
        Tuple6Shrinker(arb1, arb2, arb3, arb4, arb5, arb6)
)

internal class Tuple7Arbitary<T1, T2, T3, T4, T5, T6, T7>(
        arb1: Arbitrary<T1>,
        arb2: Arbitrary<T2>,
        arb3: Arbitrary<T3>,
        arb4: Arbitrary<T4>,
        arb5: Arbitrary<T5>,
        arb6: Arbitrary<T6>,
        arb7: Arbitrary<T7>
) : Arbitrary<Tuple7<T1, T2, T3, T4, T5, T6, T7>>(
        Tuple7Gen(arb1, arb2, arb3, arb4, arb5, arb6, arb7),
        Tuple7Shrinker(arb1, arb2, arb3, arb4, arb5, arb6, arb7)
)

internal class Tuple8Arbitary<T1, T2, T3, T4, T5, T6, T7, T8>(
        arb1: Arbitrary<T1>,
        arb2: Arbitrary<T2>,
        arb3: Arbitrary<T3>,
        arb4: Arbitrary<T4>,
        arb5: Arbitrary<T5>,
        arb6: Arbitrary<T6>,
        arb7: Arbitrary<T7>,
        arb8: Arbitrary<T8>
) : Arbitrary<Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>>(
        Tuple8Gen(arb1, arb2, arb3, arb4, arb5, arb6, arb7, arb8),
        Tuple8Shrinker(arb1, arb2, arb3, arb4, arb5, arb6, arb7, arb8)
)

internal class Tuple9Arbitary<T1, T2, T3, T4, T5, T6, T7, T8, T9>(
        arb1: Arbitrary<T1>,
        arb2: Arbitrary<T2>,
        arb3: Arbitrary<T3>,
        arb4: Arbitrary<T4>,
        arb5: Arbitrary<T5>,
        arb6: Arbitrary<T6>,
        arb7: Arbitrary<T7>,
        arb8: Arbitrary<T8>,
        arb9: Arbitrary<T9>
) : Arbitrary<Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>>(
        Tuple9Gen(arb1, arb2, arb3, arb4, arb5, arb6, arb7, arb8, arb9),
        Tuple9Shrinker(arb1, arb2, arb3, arb4, arb5, arb6, arb7, arb8, arb9)
)
