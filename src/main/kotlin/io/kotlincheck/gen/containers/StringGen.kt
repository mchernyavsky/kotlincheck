package io.kotlincheck.gen.containers

import io.kotlincheck.gen.CharGen
import io.kotlincheck.gen.Gen
import io.kotlincheck.shrink.containers.StringShrinker

class StringGen(
        val lengthBound: Int = Gen.DEFAULT_CONTAINER_SIZE_BOUND,
        val fixedLength: Boolean = false
) : Gen<String>(StringShrinker(lengthBound, fixedLength)) {
    private val charListsGen = ListGen(CharGen(), lengthBound, fixedLength)

    override fun generate(): String = charListsGen.generate().joinToString("")
}
