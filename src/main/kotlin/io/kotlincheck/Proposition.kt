package io.kotlincheck

import io.kotlintest.TestCase


object Proposition {
    val DEFAULT_TIMES = 300

    fun <P> forAll(
            gen: Gen<P>,
            times: Int,
            test: (P) -> Boolean
    ): () -> Unit = {
        repeat(times) {
            val arg = gen.generate()
            if (!test(arg)) {
                throw AssertionError("Proposition failed for arg = $arg")
            }
        }
    }

    fun <P1, P2> forAll(
            gen1: Gen<P1>,
            gen2: Gen<P2>,
            times: Int,
            test: (P1, P2) -> Boolean
    ): () -> Unit = {
        repeat(times) {
            val arg1 = gen1.generate()
            val arg2 = gen2.generate()
            if (!test(arg1, arg2)) {
                throw AssertionError("Proposition failed for arg1 = $arg1; arg2 = $arg2")
            }
        }
    }

    fun <P1, P2, P3> forAll(
            gen1: Gen<P1>,
            gen2: Gen<P2>,
            gen3: Gen<P3>,
            times: Int,
            test: (P1, P2, P3) -> Boolean
    ): () -> Unit = {
        repeat(times) {
            val arg1 = gen1.generate()
            val arg2 = gen2.generate()
            val arg3 = gen3.generate()
            if (!test(arg1, arg2, arg3)) {
                throw AssertionError("Proposition failed for arg1 = $arg1; arg2 = $arg2; arg3 = $arg3")
            }
        }
    }

    fun <P1, P2, P3, P4> forAll(
            gen1: Gen<P1>,
            gen2: Gen<P2>,
            gen3: Gen<P3>,
            gen4: Gen<P4>,
            times: Int,
            test: (P1, P2, P3, P4) -> Boolean
    ): () -> Unit = {
        repeat(times) {
            val arg1 = gen1.generate()
            val arg2 = gen2.generate()
            val arg3 = gen3.generate()
            val arg4 = gen4.generate()
            if (!test(arg1, arg2, arg3, arg4)) {
                throw AssertionError("Proposition failed for " +
                                     "arg1 = $arg1; arg2 = $arg2; arg3 = $arg3; " +
                                     "arg4 = $arg4")
            }
        }
    }

    fun <P1, P2, P3, P4, P5> forAll(
            gen1: Gen<P1>,
            gen2: Gen<P2>,
            gen3: Gen<P3>,
            gen4: Gen<P4>,
            gen5: Gen<P5>,
            times: Int,
            test: (P1, P2, P3, P4, P5) -> Boolean
    ): () -> Unit = {
        repeat(times) {
            val arg1 = gen1.generate()
            val arg2 = gen2.generate()
            val arg3 = gen3.generate()
            val arg4 = gen4.generate()
            val arg5 = gen5.generate()
            if (!test(arg1, arg2, arg3, arg4, arg5)) {
                throw AssertionError("Proposition failed for " +
                                     "arg1 = $arg1; arg2 = $arg2; arg3 = $arg3; " +
                                     "arg4 = $arg4; arg5 = $arg5")
            }
        }
    }

    fun <P1, P2, P3, P4, P5, P6> forAll(
            gen1: Gen<P1>,
            gen2: Gen<P2>,
            gen3: Gen<P3>,
            gen4: Gen<P4>,
            gen5: Gen<P5>,
            gen6: Gen<P6>,
            times: Int,
            test: (P1, P2, P3, P4, P5, P6) -> Boolean
    ): () -> Unit = {
        repeat(times) {
            val arg1 = gen1.generate()
            val arg2 = gen2.generate()
            val arg3 = gen3.generate()
            val arg4 = gen4.generate()
            val arg5 = gen5.generate()
            val arg6 = gen6.generate()
            if (!test(arg1, arg2, arg3, arg4, arg5, arg6)) {
                throw AssertionError("Proposition failed for " +
                                     "arg1 = $arg1; arg2 = $arg2; arg3 = $arg3; " +
                                     "arg4 = $arg4; arg5 = $arg5; arg6 = $arg6")
            }
        }
    }

    fun <P1, P2, P3, P4, P5, P6, P7> forAll(
            gen1: Gen<P1>,
            gen2: Gen<P2>,
            gen3: Gen<P3>,
            gen4: Gen<P4>,
            gen5: Gen<P5>,
            gen6: Gen<P6>,
            gen7: Gen<P7>,
            times: Int,
            test: (P1, P2, P3, P4, P5, P6, P7) -> Boolean
    ): () -> Unit = {
        repeat(times) {
            val arg1 = gen1.generate()
            val arg2 = gen2.generate()
            val arg3 = gen3.generate()
            val arg4 = gen4.generate()
            val arg5 = gen5.generate()
            val arg6 = gen6.generate()
            val arg7 = gen7.generate()
            if (!test(arg1, arg2, arg3, arg4, arg5, arg6, arg7)) {
                throw AssertionError("Proposition failed for " +
                                     "arg1 = $arg1; arg2 = $arg2; arg3 = $arg3; " +
                                     "arg4 = $arg4; arg5 = $arg5; arg6 = $arg6; " +
                                     "arg7 = $arg7")
            }
        }
    }

    fun <P1, P2, P3, P4, P5, P6, P7, P8> forAll(
            gen1: Gen<P1>,
            gen2: Gen<P2>,
            gen3: Gen<P3>,
            gen4: Gen<P4>,
            gen5: Gen<P5>,
            gen6: Gen<P6>,
            gen7: Gen<P7>,
            gen8: Gen<P8>,
            times: Int,
            test: (P1, P2, P3, P4, P5, P6, P7, P8) -> Boolean
    ): () -> Unit = {
        repeat(times) {
            val arg1 = gen1.generate()
            val arg2 = gen2.generate()
            val arg3 = gen3.generate()
            val arg4 = gen4.generate()
            val arg5 = gen5.generate()
            val arg6 = gen6.generate()
            val arg7 = gen7.generate()
            val arg8 = gen8.generate()
            if (!test(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8)) {
                throw AssertionError("Proposition failed for " +
                                     "arg1 = $arg1; arg2 = $arg2; arg3 = $arg3; " +
                                     "arg4 = $arg4; arg5 = $arg5; arg6 = $arg6; " +
                                     "arg7 = $arg7; arg8 = $arg8")
            }
        }
    }

    fun <P1, P2, P3, P4, P5, P6, P7, P8, P9> forAll(
            gen1: Gen<P1>,
            gen2: Gen<P2>,
            gen3: Gen<P3>,
            gen4: Gen<P4>,
            gen5: Gen<P5>,
            gen6: Gen<P6>,
            gen7: Gen<P7>,
            gen8: Gen<P8>,
            gen9: Gen<P9>,
            times: Int,
            test: (P1, P2, P3, P4, P5, P6, P7, P8, P9) -> Boolean
    ): () -> Unit = {
        repeat(times) {
            val arg1 = gen1.generate()
            val arg2 = gen2.generate()
            val arg3 = gen3.generate()
            val arg4 = gen4.generate()
            val arg5 = gen5.generate()
            val arg6 = gen6.generate()
            val arg7 = gen7.generate()
            val arg8 = gen8.generate()
            val arg9 = gen9.generate()
            if (!test(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9)) {
                throw AssertionError("Proposition failed for " +
                                     "arg1 = $arg1; arg2 = $arg2; arg3 = $arg3; " +
                                     "arg4 = $arg4; arg5 = $arg5; arg6 = $arg6; " +
                                     "arg7 = $arg7; arg8 = $arg8; arg9 = $arg9")
            }
        }
    }
}


/** PropSpec API extensions */

inline fun <reified P> PropSpec.forAll(
        gen: Gen<P> = Gen.default<P>(),
        times: Int = Proposition.DEFAULT_TIMES,
        noinline test: (P) -> Boolean
): TestCase {
    val testCase = createTestCaseForCurrentSuite(Proposition.forAll(gen, times, test))
    addTestCaseToCurrentSuite(testCase)
    return testCase
}

inline fun <reified P1, reified P2> PropSpec.forAll(
        gen1: Gen<P1> = Gen.default<P1>(),
        gen2: Gen<P2> = Gen.default<P2>(),
        times: Int = Proposition.DEFAULT_TIMES,
        noinline test: (P1, P2) -> Boolean
): TestCase {
    val testCase = createTestCaseForCurrentSuite(Proposition.forAll(gen1, gen2, times, test))
    addTestCaseToCurrentSuite(testCase)
    return testCase
}

inline fun <reified P1, reified P2, reified P3> PropSpec.forAll(
        gen1: Gen<P1> = Gen.default<P1>(),
        gen2: Gen<P2> = Gen.default<P2>(),
        gen3: Gen<P3> = Gen.default<P3>(),
        times: Int = Proposition.DEFAULT_TIMES,
        noinline test: (P1, P2, P3) -> Boolean
): TestCase {
    val testCase = createTestCaseForCurrentSuite(Proposition.forAll(gen1, gen2, gen3, times, test))
    addTestCaseToCurrentSuite(testCase)
    return testCase
}

inline fun <reified P1, reified P2, reified P3,
            reified P4> PropSpec.forAll(
        gen1: Gen<P1> = Gen.default<P1>(),
        gen2: Gen<P2> = Gen.default<P2>(),
        gen3: Gen<P3> = Gen.default<P3>(),
        gen4: Gen<P4> = Gen.default<P4>(),
        times: Int = Proposition.DEFAULT_TIMES,
        noinline test: (P1, P2, P3, P4) -> Boolean
): TestCase {
    val testCase = createTestCaseForCurrentSuite(
            Proposition.forAll(gen1, gen2, gen3, gen4, times, test))
    addTestCaseToCurrentSuite(testCase)
    return testCase
}

inline fun <reified P1, reified P2, reified P3,
            reified P4, reified P5> PropSpec.forAll(
        gen1: Gen<P1> = Gen.default<P1>(),
        gen2: Gen<P2> = Gen.default<P2>(),
        gen3: Gen<P3> = Gen.default<P3>(),
        gen4: Gen<P4> = Gen.default<P4>(),
        gen5: Gen<P5> = Gen.default<P5>(),
        times: Int = Proposition.DEFAULT_TIMES,
        noinline test: (P1, P2, P3, P4, P5) -> Boolean
): TestCase {
    val testCase = createTestCaseForCurrentSuite(
            Proposition.forAll(gen1, gen2, gen3, gen4, gen5, times, test))
    addTestCaseToCurrentSuite(testCase)
    return testCase
}

inline fun <reified P1, reified P2, reified P3,
            reified P4, reified P5, reified P6> PropSpec.forAll(
        gen1: Gen<P1> = Gen.default<P1>(),
        gen2: Gen<P2> = Gen.default<P2>(),
        gen3: Gen<P3> = Gen.default<P3>(),
        gen4: Gen<P4> = Gen.default<P4>(),
        gen5: Gen<P5> = Gen.default<P5>(),
        gen6: Gen<P6> = Gen.default<P6>(),
        times: Int = Proposition.DEFAULT_TIMES,
        noinline test: (P1, P2, P3, P4, P5, P6) -> Boolean
): TestCase {
    val testCase = createTestCaseForCurrentSuite(
            Proposition.forAll(gen1, gen2, gen3, gen4, gen5, gen6, times, test))
    addTestCaseToCurrentSuite(testCase)
    return testCase
}

inline fun <reified P1, reified P2, reified P3,
            reified P4, reified P5, reified P6,
            reified P7> PropSpec.forAll(
        gen1: Gen<P1> = Gen.default<P1>(),
        gen2: Gen<P2> = Gen.default<P2>(),
        gen3: Gen<P3> = Gen.default<P3>(),
        gen4: Gen<P4> = Gen.default<P4>(),
        gen5: Gen<P5> = Gen.default<P5>(),
        gen6: Gen<P6> = Gen.default<P6>(),
        gen7: Gen<P7> = Gen.default<P7>(),
        times: Int = Proposition.DEFAULT_TIMES,
        noinline test: (P1, P2, P3, P4, P5, P6, P7) -> Boolean
): TestCase {
    val testCase = createTestCaseForCurrentSuite(
            Proposition.forAll(gen1, gen2, gen3, gen4, gen5, gen6, gen7, times, test))
    addTestCaseToCurrentSuite(testCase)
    return testCase
}

inline fun <reified P1, reified P2, reified P3,
            reified P4, reified P5, reified P6,
            reified P7, reified P8> PropSpec.forAll(
        gen1: Gen<P1> = Gen.default<P1>(),
        gen2: Gen<P2> = Gen.default<P2>(),
        gen3: Gen<P3> = Gen.default<P3>(),
        gen4: Gen<P4> = Gen.default<P4>(),
        gen5: Gen<P5> = Gen.default<P5>(),
        gen6: Gen<P6> = Gen.default<P6>(),
        gen7: Gen<P7> = Gen.default<P7>(),
        gen8: Gen<P8> = Gen.default<P8>(),
        times: Int = Proposition.DEFAULT_TIMES,
        noinline test: (P1, P2, P3, P4, P5, P6, P7, P8) -> Boolean
): TestCase {
    val testCase = createTestCaseForCurrentSuite(
            Proposition.forAll(gen1, gen2, gen3, gen4, gen5, gen6, gen7, gen8, times, test))
    addTestCaseToCurrentSuite(testCase)
    return testCase
}

inline fun <reified P1, reified P2, reified P3,
            reified P4, reified P5, reified P6,
            reified P7, reified P8, reified P9> PropSpec.forAll(
        gen1: Gen<P1> = Gen.default<P1>(),
        gen2: Gen<P2> = Gen.default<P2>(),
        gen3: Gen<P3> = Gen.default<P3>(),
        gen4: Gen<P4> = Gen.default<P4>(),
        gen5: Gen<P5> = Gen.default<P5>(),
        gen6: Gen<P6> = Gen.default<P6>(),
        gen7: Gen<P7> = Gen.default<P7>(),
        gen8: Gen<P8> = Gen.default<P8>(),
        gen9: Gen<P9> = Gen.default<P9>(),
        times: Int = Proposition.DEFAULT_TIMES,
        noinline test: (P1, P2, P3, P4, P5, P6, P7, P8, P9) -> Boolean
): TestCase {
    val testCase = createTestCaseForCurrentSuite(
            Proposition.forAll(gen1, gen2, gen3, gen4, gen5, gen6, gen7, gen8, gen9, times, test))
    addTestCaseToCurrentSuite(testCase)
    return testCase
}
