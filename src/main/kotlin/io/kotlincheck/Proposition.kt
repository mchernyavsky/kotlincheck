package io.kotlincheck

import io.kotlincheck.arbitrary.Arbitrary
import io.kotlintest.TestCase


object Proposition {
    val DEFAULT_TIMES = 300

    fun <P> forAll(
            arb: Arbitrary<P>,
            times: Int,
            test: (P) -> Boolean
    ): () -> Unit = {
        repeat(times) {
            val arg = arb.generate()
            if (!test(arg)) {
                throw AssertionError("Proposition failed for arg = $arg")
            }
        }
    }

    fun <P1, P2> forAll(
            arb1: Arbitrary<P1>,
            arb2: Arbitrary<P2>,
            times: Int,
            test: (P1, P2) -> Boolean
    ): () -> Unit = {
        repeat(times) {
            val arg1 = arb1.generate()
            val arg2 = arb2.generate()
            if (!test(arg1, arg2)) {
                throw AssertionError("Proposition failed for arg1 = $arg1; arg2 = $arg2")
            }
        }
    }

    fun <P1, P2, P3> forAll(
            arb1: Arbitrary<P1>,
            arb2: Arbitrary<P2>,
            arb3: Arbitrary<P3>,
            times: Int,
            test: (P1, P2, P3) -> Boolean
    ): () -> Unit = {
        repeat(times) {
            val arg1 = arb1.generate()
            val arg2 = arb2.generate()
            val arg3 = arb3.generate()
            if (!test(arg1, arg2, arg3)) {
                throw AssertionError("Proposition failed for arg1 = $arg1; arg2 = $arg2; arg3 = $arg3")
            }
        }
    }

    fun <P1, P2, P3, P4> forAll(
            arb1: Arbitrary<P1>,
            arb2: Arbitrary<P2>,
            arb3: Arbitrary<P3>,
            arb4: Arbitrary<P4>,
            times: Int,
            test: (P1, P2, P3, P4) -> Boolean
    ): () -> Unit = {
        repeat(times) {
            val arg1 = arb1.generate()
            val arg2 = arb2.generate()
            val arg3 = arb3.generate()
            val arg4 = arb4.generate()
            if (!test(arg1, arg2, arg3, arg4)) {
                throw AssertionError("Proposition failed for " +
                                     "arg1 = $arg1; arg2 = $arg2; arg3 = $arg3; " +
                                     "arg4 = $arg4")
            }
        }
    }

    fun <P1, P2, P3, P4, P5> forAll(
            arb1: Arbitrary<P1>,
            arb2: Arbitrary<P2>,
            arb3: Arbitrary<P3>,
            arb4: Arbitrary<P4>,
            arb5: Arbitrary<P5>,
            times: Int,
            test: (P1, P2, P3, P4, P5) -> Boolean
    ): () -> Unit = {
        repeat(times) {
            val arg1 = arb1.generate()
            val arg2 = arb2.generate()
            val arg3 = arb3.generate()
            val arg4 = arb4.generate()
            val arg5 = arb5.generate()
            if (!test(arg1, arg2, arg3, arg4, arg5)) {
                throw AssertionError("Proposition failed for " +
                                     "arg1 = $arg1; arg2 = $arg2; arg3 = $arg3; " +
                                     "arg4 = $arg4; arg5 = $arg5")
            }
        }
    }

    fun <P1, P2, P3, P4, P5, P6> forAll(
            arb1: Arbitrary<P1>,
            arb2: Arbitrary<P2>,
            arb3: Arbitrary<P3>,
            arb4: Arbitrary<P4>,
            arb5: Arbitrary<P5>,
            arb6: Arbitrary<P6>,
            times: Int,
            test: (P1, P2, P3, P4, P5, P6) -> Boolean
    ): () -> Unit = {
        repeat(times) {
            val arg1 = arb1.generate()
            val arg2 = arb2.generate()
            val arg3 = arb3.generate()
            val arg4 = arb4.generate()
            val arg5 = arb5.generate()
            val arg6 = arb6.generate()
            if (!test(arg1, arg2, arg3, arg4, arg5, arg6)) {
                throw AssertionError("Proposition failed for " +
                                     "arg1 = $arg1; arg2 = $arg2; arg3 = $arg3; " +
                                     "arg4 = $arg4; arg5 = $arg5; arg6 = $arg6")
            }
        }
    }

    fun <P1, P2, P3, P4, P5, P6, P7> forAll(
            arb1: Arbitrary<P1>,
            arb2: Arbitrary<P2>,
            arb3: Arbitrary<P3>,
            arb4: Arbitrary<P4>,
            arb5: Arbitrary<P5>,
            arb6: Arbitrary<P6>,
            arb7: Arbitrary<P7>,
            times: Int,
            test: (P1, P2, P3, P4, P5, P6, P7) -> Boolean
    ): () -> Unit = {
        repeat(times) {
            val arg1 = arb1.generate()
            val arg2 = arb2.generate()
            val arg3 = arb3.generate()
            val arg4 = arb4.generate()
            val arg5 = arb5.generate()
            val arg6 = arb6.generate()
            val arg7 = arb7.generate()
            if (!test(arg1, arg2, arg3, arg4, arg5, arg6, arg7)) {
                throw AssertionError("Proposition failed for " +
                                     "arg1 = $arg1; arg2 = $arg2; arg3 = $arg3; " +
                                     "arg4 = $arg4; arg5 = $arg5; arg6 = $arg6; " +
                                     "arg7 = $arg7")
            }
        }
    }

    fun <P1, P2, P3, P4, P5, P6, P7, P8> forAll(
            arb1: Arbitrary<P1>,
            arb2: Arbitrary<P2>,
            arb3: Arbitrary<P3>,
            arb4: Arbitrary<P4>,
            arb5: Arbitrary<P5>,
            arb6: Arbitrary<P6>,
            arb7: Arbitrary<P7>,
            arb8: Arbitrary<P8>,
            times: Int,
            test: (P1, P2, P3, P4, P5, P6, P7, P8) -> Boolean
    ): () -> Unit = {
        repeat(times) {
            val arg1 = arb1.generate()
            val arg2 = arb2.generate()
            val arg3 = arb3.generate()
            val arg4 = arb4.generate()
            val arg5 = arb5.generate()
            val arg6 = arb6.generate()
            val arg7 = arb7.generate()
            val arg8 = arb8.generate()
            if (!test(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8)) {
                throw AssertionError("Proposition failed for " +
                                     "arg1 = $arg1; arg2 = $arg2; arg3 = $arg3; " +
                                     "arg4 = $arg4; arg5 = $arg5; arg6 = $arg6; " +
                                     "arg7 = $arg7; arg8 = $arg8")
            }
        }
    }

    fun <P1, P2, P3, P4, P5, P6, P7, P8, P9> forAll(
            arb1: Arbitrary<P1>,
            arb2: Arbitrary<P2>,
            arb3: Arbitrary<P3>,
            arb4: Arbitrary<P4>,
            arb5: Arbitrary<P5>,
            arb6: Arbitrary<P6>,
            arb7: Arbitrary<P7>,
            arb8: Arbitrary<P8>,
            arb9: Arbitrary<P9>,
            times: Int,
            test: (P1, P2, P3, P4, P5, P6, P7, P8, P9) -> Boolean
    ): () -> Unit = {
        repeat(times) {
            val arg1 = arb1.generate()
            val arg2 = arb2.generate()
            val arg3 = arb3.generate()
            val arg4 = arb4.generate()
            val arg5 = arb5.generate()
            val arg6 = arb6.generate()
            val arg7 = arb7.generate()
            val arg8 = arb8.generate()
            val arg9 = arb9.generate()
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
        gen: Arbitrary<P> = Arbitrary.default<P>(),
        times: Int = Proposition.DEFAULT_TIMES,
        noinline test: (P) -> Boolean
): TestCase {
    val testCase = createTestCaseForCurrentSuite(Proposition.forAll(gen, times, test))
    addTestCaseToCurrentSuite(testCase)
    return testCase
}

inline fun <reified P1, reified P2> PropSpec.forAll(
        arb1: Arbitrary<P1> = Arbitrary.default<P1>(),
        arb2: Arbitrary<P2> = Arbitrary.default<P2>(),
        times: Int = Proposition.DEFAULT_TIMES,
        noinline test: (P1, P2) -> Boolean
): TestCase {
    val testCase = createTestCaseForCurrentSuite(Proposition.forAll(arb1, arb2, times, test))
    addTestCaseToCurrentSuite(testCase)
    return testCase
}

inline fun <reified P1, reified P2, reified P3> PropSpec.forAll(
        arb1: Arbitrary<P1> = Arbitrary.default<P1>(),
        arb2: Arbitrary<P2> = Arbitrary.default<P2>(),
        arb3: Arbitrary<P3> = Arbitrary.default<P3>(),
        times: Int = Proposition.DEFAULT_TIMES,
        noinline test: (P1, P2, P3) -> Boolean
): TestCase {
    val testCase = createTestCaseForCurrentSuite(Proposition.forAll(arb1, arb2, arb3, times, test))
    addTestCaseToCurrentSuite(testCase)
    return testCase
}

inline fun <reified P1, reified P2, reified P3,
            reified P4> PropSpec.forAll(
        arb1: Arbitrary<P1> = Arbitrary.default<P1>(),
        arb2: Arbitrary<P2> = Arbitrary.default<P2>(),
        arb3: Arbitrary<P3> = Arbitrary.default<P3>(),
        arb4: Arbitrary<P4> = Arbitrary.default<P4>(),
        times: Int = Proposition.DEFAULT_TIMES,
        noinline test: (P1, P2, P3, P4) -> Boolean
): TestCase {
    val testCase = createTestCaseForCurrentSuite(
            Proposition.forAll(arb1, arb2, arb3, arb4, times, test))
    addTestCaseToCurrentSuite(testCase)
    return testCase
}

inline fun <reified P1, reified P2, reified P3,
            reified P4, reified P5> PropSpec.forAll(
        arb1: Arbitrary<P1> = Arbitrary.default<P1>(),
        arb2: Arbitrary<P2> = Arbitrary.default<P2>(),
        arb3: Arbitrary<P3> = Arbitrary.default<P3>(),
        arb4: Arbitrary<P4> = Arbitrary.default<P4>(),
        arb5: Arbitrary<P5> = Arbitrary.default<P5>(),
        times: Int = Proposition.DEFAULT_TIMES,
        noinline test: (P1, P2, P3, P4, P5) -> Boolean
): TestCase {
    val testCase = createTestCaseForCurrentSuite(
            Proposition.forAll(arb1, arb2, arb3, arb4, arb5, times, test))
    addTestCaseToCurrentSuite(testCase)
    return testCase
}

inline fun <reified P1, reified P2, reified P3,
            reified P4, reified P5, reified P6> PropSpec.forAll(
        arb1: Arbitrary<P1> = Arbitrary.default<P1>(),
        arb2: Arbitrary<P2> = Arbitrary.default<P2>(),
        arb3: Arbitrary<P3> = Arbitrary.default<P3>(),
        arb4: Arbitrary<P4> = Arbitrary.default<P4>(),
        arb5: Arbitrary<P5> = Arbitrary.default<P5>(),
        arb6: Arbitrary<P6> = Arbitrary.default<P6>(),
        times: Int = Proposition.DEFAULT_TIMES,
        noinline test: (P1, P2, P3, P4, P5, P6) -> Boolean
): TestCase {
    val testCase = createTestCaseForCurrentSuite(
            Proposition.forAll(arb1, arb2, arb3, arb4, arb5, arb6, times, test))
    addTestCaseToCurrentSuite(testCase)
    return testCase
}

inline fun <reified P1, reified P2, reified P3,
            reified P4, reified P5, reified P6,
            reified P7> PropSpec.forAll(
        arb1: Arbitrary<P1> = Arbitrary.default<P1>(),
        arb2: Arbitrary<P2> = Arbitrary.default<P2>(),
        arb3: Arbitrary<P3> = Arbitrary.default<P3>(),
        arb4: Arbitrary<P4> = Arbitrary.default<P4>(),
        arb5: Arbitrary<P5> = Arbitrary.default<P5>(),
        arb6: Arbitrary<P6> = Arbitrary.default<P6>(),
        arb7: Arbitrary<P7> = Arbitrary.default<P7>(),
        times: Int = Proposition.DEFAULT_TIMES,
        noinline test: (P1, P2, P3, P4, P5, P6, P7) -> Boolean
): TestCase {
    val testCase = createTestCaseForCurrentSuite(
            Proposition.forAll(arb1, arb2, arb3, arb4, arb5, arb6, arb7, times, test))
    addTestCaseToCurrentSuite(testCase)
    return testCase
}

inline fun <reified P1, reified P2, reified P3,
            reified P4, reified P5, reified P6,
            reified P7, reified P8> PropSpec.forAll(
        arb1: Arbitrary<P1> = Arbitrary.default<P1>(),
        arb2: Arbitrary<P2> = Arbitrary.default<P2>(),
        arb3: Arbitrary<P3> = Arbitrary.default<P3>(),
        arb4: Arbitrary<P4> = Arbitrary.default<P4>(),
        arb5: Arbitrary<P5> = Arbitrary.default<P5>(),
        arb6: Arbitrary<P6> = Arbitrary.default<P6>(),
        arb7: Arbitrary<P7> = Arbitrary.default<P7>(),
        arb8: Arbitrary<P8> = Arbitrary.default<P8>(),
        times: Int = Proposition.DEFAULT_TIMES,
        noinline test: (P1, P2, P3, P4, P5, P6, P7, P8) -> Boolean
): TestCase {
    val testCase = createTestCaseForCurrentSuite(
            Proposition.forAll(arb1, arb2, arb3, arb4, arb5, arb6, arb7, arb8, times, test))
    addTestCaseToCurrentSuite(testCase)
    return testCase
}

inline fun <reified P1, reified P2, reified P3,
            reified P4, reified P5, reified P6,
            reified P7, reified P8, reified P9> PropSpec.forAll(
        arb1: Arbitrary<P1> = Arbitrary.default<P1>(),
        arb2: Arbitrary<P2> = Arbitrary.default<P2>(),
        arb3: Arbitrary<P3> = Arbitrary.default<P3>(),
        arb4: Arbitrary<P4> = Arbitrary.default<P4>(),
        arb5: Arbitrary<P5> = Arbitrary.default<P5>(),
        arb6: Arbitrary<P6> = Arbitrary.default<P6>(),
        arb7: Arbitrary<P7> = Arbitrary.default<P7>(),
        arb8: Arbitrary<P8> = Arbitrary.default<P8>(),
        arb9: Arbitrary<P9> = Arbitrary.default<P9>(),
        times: Int = Proposition.DEFAULT_TIMES,
        noinline test: (P1, P2, P3, P4, P5, P6, P7, P8, P9) -> Boolean
): TestCase {
    val testCase = createTestCaseForCurrentSuite(
            Proposition.forAll(arb1, arb2, arb3, arb4, arb5, arb6, arb7, arb8, arb9, times, test))
    addTestCaseToCurrentSuite(testCase)
    return testCase
}
