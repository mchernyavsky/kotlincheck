package io.kotlincheck.gen

class PairGen<out A, out B>(val gen1: Gen<A>, val gen2: Gen<B>
) : Gen<Pair<A, B>> {
    override fun generate(): Pair<A, B> = Pair(gen1.generate(), gen2.generate())
}

class TripleGen<out A, out B, out C>(val gen1: Gen<A>, val gen2: Gen<B>, val gen3: Gen<C>
) : Gen<Triple<A, B, C>> {
    override fun generate(): Triple<A, B, C> = Triple(gen1.generate(), gen2.generate(), gen3.generate())
}
