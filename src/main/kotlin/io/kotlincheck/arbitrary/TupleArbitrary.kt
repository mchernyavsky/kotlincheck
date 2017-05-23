package io.kotlincheck.arbitrary

import io.kotlincheck.gen.PairGen
import io.kotlincheck.gen.TripleGen
import io.kotlincheck.shrink.PairShrinker
import io.kotlincheck.shrink.TripleShrinker

class PairArbitary<A, B>(arb1: Arbitrary<A>, arb2: Arbitrary<B>
) : Arbitrary<Pair<A, B>>(PairGen(arb1, arb2), PairShrinker(arb1, arb2))

class TripleArbitary<A, B, C>(arb1: Arbitrary<A>, arb2: Arbitrary<B>, arb3: Arbitrary<C>
) : Arbitrary<Triple<A, B, C>>(TripleGen(arb1, arb2, arb3), TripleShrinker(arb1, arb2, arb3))
