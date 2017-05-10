package io.kotlincheck.shrink

class ByteShrinker(val origin: Byte, val bound: Byte) : Shrinker<Byte> {
    private val longShrinker = LongShrinker(origin.toLong(), bound.toLong())

    override fun shrink(
            test: (Byte) -> Boolean,
            counterexample: Byte
    ): Byte = longShrinker.shrink(
            { test(it.toByte()) },
            counterexample.toLong()
    ).toByte()
}
