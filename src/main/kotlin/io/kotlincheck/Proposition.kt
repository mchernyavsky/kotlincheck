package io.kotlincheck

import io.kotlincheck.arbitrary.*
import io.kotlintest.TestCase
import java.io.Serializable

object Proposition {
    const val DEFAULT_TIMES: Int = 300

    fun <P> forAll(
        propositionFullName: String,
        arb: Arbitrary<P>, times: Int,
        test: (P) -> Boolean
    ): () -> Unit = {
        val oldCounterexamples = CounterexampleStorage.loadCounterexamples<P>(propositionFullName)
        try {
            oldCounterexamples
                .filterNot(arb::isAcceptable)
                .forEach { CounterexampleStorage.removeCounterexample(propositionFullName, it as Serializable) }
            oldCounterexamples
                .filter(arb::isAcceptable)
                .find { !test(it) }
                ?.let { throw AssertionError("Proposition failed. Counterexample: $it") }
        } catch (e: ClassCastException) {
            CounterexampleStorage.removeCounterexamples(propositionFullName)
        }

        repeat(times) {
            var arg = arb.generate()
            if (!test(arg)) {
                arg = arb.shrink(test, arg)
                if (arg is Serializable) {
                    CounterexampleStorage.saveCounterexample(propositionFullName, arg)
                }
                throw AssertionError("Proposition failed. Counterexample: $arg")
            }
        }
    }

    fun <P1, P2> forAll(
        propositionFullName: String,
        arb1: Arbitrary<P1>,
        arb2: Arbitrary<P2>,
        times: Int,
        test: (P1, P2) -> Boolean
    ): () -> Unit {
        val arbs = Tuple2Arbitrary(arb1, arb2)
        val testTupled = { (arg1, arg2): Tuple2<P1, P2> -> test(arg1, arg2) }
        return forAll(propositionFullName, arbs, times, testTupled)
    }

    fun <P1, P2, P3> forAll(
        propositionFullName: String,
        arb1: Arbitrary<P1>,
        arb2: Arbitrary<P2>,
        arb3: Arbitrary<P3>,
        times: Int,
        test: (P1, P2, P3) -> Boolean
    ): () -> Unit {
        val arbs = Tuple3Arbitrary(arb1, arb2, arb3)
        val testTupled = { (arg1, arg2, arg3): Tuple3<P1, P2, P3> -> test(arg1, arg2, arg3) }
        return forAll(propositionFullName, arbs, times, testTupled)
    }

    fun <P1, P2, P3, P4> forAll(
        propositionFullName: String,
        arb1: Arbitrary<P1>,
        arb2: Arbitrary<P2>,
        arb3: Arbitrary<P3>,
        arb4: Arbitrary<P4>,
        times: Int,
        test: (P1, P2, P3, P4) -> Boolean
    ): () -> Unit {
        val arbs = Tuple4Arbitrary(arb1, arb2, arb3, arb4)
        val testTupled = { (arg1, arg2, arg3, arg4): Tuple4<P1, P2, P3, P4> -> test(arg1, arg2, arg3, arg4) }
        return forAll(propositionFullName, arbs, times, testTupled)
    }

    fun <P1, P2, P3, P4, P5> forAll(
        propositionFullName: String,
        arb1: Arbitrary<P1>,
        arb2: Arbitrary<P2>,
        arb3: Arbitrary<P3>,
        arb4: Arbitrary<P4>,
        arb5: Arbitrary<P5>,
        times: Int,
        test: (P1, P2, P3, P4, P5) -> Boolean
    ): () -> Unit {
        val arbs = Tuple5Arbitrary(arb1, arb2, arb3, arb4, arb5)
        val testTupled = { (arg1, arg2, arg3, arg4, arg5): Tuple5<P1, P2, P3, P4, P5> ->
            test(arg1, arg2, arg3, arg4, arg5)
        }
        return forAll(propositionFullName, arbs, times, testTupled)
    }

    fun <P1, P2, P3, P4, P5, P6> forAll(
        propositionFullName: String,
        arb1: Arbitrary<P1>,
        arb2: Arbitrary<P2>,
        arb3: Arbitrary<P3>,
        arb4: Arbitrary<P4>,
        arb5: Arbitrary<P5>,
        arb6: Arbitrary<P6>,
        times: Int,
        test: (P1, P2, P3, P4, P5, P6) -> Boolean
    ): () -> Unit {
        val arbs = Tuple6Arbitrary(arb1, arb2, arb3, arb4, arb5, arb6)
        val testTupled = { (arg1, arg2, arg3, arg4, arg5, arg6): Tuple6<P1, P2, P3, P4, P5, P6> ->
            test(arg1, arg2, arg3, arg4, arg5, arg6)
        }
        return forAll(propositionFullName, arbs, times, testTupled)
    }

    fun <P1, P2, P3, P4, P5, P6, P7> forAll(
        propositionFullName: String,
        arb1: Arbitrary<P1>,
        arb2: Arbitrary<P2>,
        arb3: Arbitrary<P3>,
        arb4: Arbitrary<P4>,
        arb5: Arbitrary<P5>,
        arb6: Arbitrary<P6>,
        arb7: Arbitrary<P7>,
        times: Int,
        test: (P1, P2, P3, P4, P5, P6, P7) -> Boolean
    ): () -> Unit {
        val arbs = Tuple7Arbitrary(arb1, arb2, arb3, arb4, arb5, arb6, arb7)
        val testTupled = { (arg1, arg2, arg3, arg4, arg5, arg6, arg7): Tuple7<P1, P2, P3, P4, P5, P6, P7> ->
            test(arg1, arg2, arg3, arg4, arg5, arg6, arg7)
        }
        return forAll(propositionFullName, arbs, times, testTupled)
    }

    fun <P1, P2, P3, P4, P5, P6, P7, P8> forAll(
        propositionFullName: String,
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
    ): () -> Unit {
        val arbs = Tuple8Arbitrary(arb1, arb2, arb3, arb4, arb5, arb6, arb7, arb8)
        val testTupled = { (arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8): Tuple8<P1, P2, P3, P4, P5, P6, P7, P8> ->
            test(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8)
        }
        return forAll(propositionFullName, arbs, times, testTupled)
    }

    fun <P1, P2, P3, P4, P5, P6, P7, P8, P9> forAll(
        propositionFullName: String,
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
    ): () -> Unit {
        val arbs = Tuple9Arbitrary(arb1, arb2, arb3, arb4, arb5, arb6, arb7, arb8, arb9)
        val testTupled =
            { (arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9): Tuple9<P1, P2, P3, P4, P5, P6, P7, P8, P9> ->
                test(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9)
            }
        return forAll(propositionFullName, arbs, times, testTupled)
    }
}

/** PropSpec API extensions */

inline fun <reified P> PropSpec.forAll(
    arb: Arbitrary<P> = Arbitrary.default(),
    times: Int = Proposition.DEFAULT_TIMES,
    noinline test: (P) -> Boolean
): TestCase {
    val testCase = createTestCaseForCurrentSuite(
        Proposition.forAll(propositionFullName, arb, times, test)
    )
    addTestCaseToCurrentSuite(testCase)
    return testCase
}

inline fun <reified P1, reified P2> PropSpec.forAll(
    arb1: Arbitrary<P1> = Arbitrary.default(),
    arb2: Arbitrary<P2> = Arbitrary.default(),
    times: Int = Proposition.DEFAULT_TIMES,
    noinline test: (P1, P2) -> Boolean
): TestCase {
    val testCase = createTestCaseForCurrentSuite(
        Proposition.forAll(propositionFullName, arb1, arb2, times, test)
    )
    addTestCaseToCurrentSuite(testCase)
    return testCase
}

inline fun <reified P1, reified P2, reified P3> PropSpec.forAll(
    arb1: Arbitrary<P1> = Arbitrary.default(),
    arb2: Arbitrary<P2> = Arbitrary.default(),
    arb3: Arbitrary<P3> = Arbitrary.default(),
    times: Int = Proposition.DEFAULT_TIMES,
    noinline test: (P1, P2, P3) -> Boolean
): TestCase {
    val testCase = createTestCaseForCurrentSuite(
        Proposition.forAll(propositionFullName, arb1, arb2, arb3, times, test)
    )
    addTestCaseToCurrentSuite(testCase)
    return testCase
}

inline fun <
        reified P1, reified P2, reified P3,
        reified P4
        > PropSpec.forAll(
    arb1: Arbitrary<P1> = Arbitrary.default(),
    arb2: Arbitrary<P2> = Arbitrary.default(),
    arb3: Arbitrary<P3> = Arbitrary.default(),
    arb4: Arbitrary<P4> = Arbitrary.default(),
    times: Int = Proposition.DEFAULT_TIMES,
    noinline test: (P1, P2, P3, P4) -> Boolean
): TestCase {
    val testCase = createTestCaseForCurrentSuite(
        Proposition.forAll(propositionFullName, arb1, arb2, arb3, arb4, times, test)
    )
    addTestCaseToCurrentSuite(testCase)
    return testCase
}

inline fun <
        reified P1, reified P2, reified P3,
        reified P4, reified P5
        > PropSpec.forAll(
    arb1: Arbitrary<P1> = Arbitrary.default(),
    arb2: Arbitrary<P2> = Arbitrary.default(),
    arb3: Arbitrary<P3> = Arbitrary.default(),
    arb4: Arbitrary<P4> = Arbitrary.default(),
    arb5: Arbitrary<P5> = Arbitrary.default(),
    times: Int = Proposition.DEFAULT_TIMES,
    noinline test: (P1, P2, P3, P4, P5) -> Boolean
): TestCase {
    val testCase = createTestCaseForCurrentSuite(
        Proposition.forAll(propositionFullName, arb1, arb2, arb3, arb4, arb5, times, test)
    )
    addTestCaseToCurrentSuite(testCase)
    return testCase
}

inline fun <
        reified P1, reified P2, reified P3,
        reified P4, reified P5, reified P6
        > PropSpec.forAll(
    arb1: Arbitrary<P1> = Arbitrary.default(),
    arb2: Arbitrary<P2> = Arbitrary.default(),
    arb3: Arbitrary<P3> = Arbitrary.default(),
    arb4: Arbitrary<P4> = Arbitrary.default(),
    arb5: Arbitrary<P5> = Arbitrary.default(),
    arb6: Arbitrary<P6> = Arbitrary.default(),
    times: Int = Proposition.DEFAULT_TIMES,
    noinline test: (P1, P2, P3, P4, P5, P6) -> Boolean
): TestCase {
    val testCase = createTestCaseForCurrentSuite(
        Proposition.forAll(propositionFullName, arb1, arb2, arb3, arb4, arb5, arb6, times, test)
    )
    addTestCaseToCurrentSuite(testCase)
    return testCase
}

inline fun <
        reified P1, reified P2, reified P3,
        reified P4, reified P5, reified P6,
        reified P7
        > PropSpec.forAll(
    arb1: Arbitrary<P1> = Arbitrary.default(),
    arb2: Arbitrary<P2> = Arbitrary.default(),
    arb3: Arbitrary<P3> = Arbitrary.default(),
    arb4: Arbitrary<P4> = Arbitrary.default(),
    arb5: Arbitrary<P5> = Arbitrary.default(),
    arb6: Arbitrary<P6> = Arbitrary.default(),
    arb7: Arbitrary<P7> = Arbitrary.default(),
    times: Int = Proposition.DEFAULT_TIMES,
    noinline test: (P1, P2, P3, P4, P5, P6, P7) -> Boolean
): TestCase {
    val testCase = createTestCaseForCurrentSuite(
        Proposition.forAll(propositionFullName, arb1, arb2, arb3, arb4, arb5, arb6, arb7, times, test)
    )
    addTestCaseToCurrentSuite(testCase)
    return testCase
}

inline fun <
        reified P1, reified P2, reified P3,
        reified P4, reified P5, reified P6,
        reified P7, reified P8
        > PropSpec.forAll(
    arb1: Arbitrary<P1> = Arbitrary.default(),
    arb2: Arbitrary<P2> = Arbitrary.default(),
    arb3: Arbitrary<P3> = Arbitrary.default(),
    arb4: Arbitrary<P4> = Arbitrary.default(),
    arb5: Arbitrary<P5> = Arbitrary.default(),
    arb6: Arbitrary<P6> = Arbitrary.default(),
    arb7: Arbitrary<P7> = Arbitrary.default(),
    arb8: Arbitrary<P8> = Arbitrary.default(),
    times: Int = Proposition.DEFAULT_TIMES,
    noinline test: (P1, P2, P3, P4, P5, P6, P7, P8) -> Boolean
): TestCase {
    val testCase = createTestCaseForCurrentSuite(
        Proposition.forAll(
            propositionFullName,
            arb1, arb2, arb3, arb4, arb5, arb6, arb7, arb8,
            times, test
        )
    )
    addTestCaseToCurrentSuite(testCase)
    return testCase
}

inline fun <
        reified P1, reified P2, reified P3,
        reified P4, reified P5, reified P6,
        reified P7, reified P8, reified P9
        > PropSpec.forAll(
    arb1: Arbitrary<P1> = Arbitrary.default(),
    arb2: Arbitrary<P2> = Arbitrary.default(),
    arb3: Arbitrary<P3> = Arbitrary.default(),
    arb4: Arbitrary<P4> = Arbitrary.default(),
    arb5: Arbitrary<P5> = Arbitrary.default(),
    arb6: Arbitrary<P6> = Arbitrary.default(),
    arb7: Arbitrary<P7> = Arbitrary.default(),
    arb8: Arbitrary<P8> = Arbitrary.default(),
    arb9: Arbitrary<P9> = Arbitrary.default(),
    times: Int = Proposition.DEFAULT_TIMES,
    noinline test: (P1, P2, P3, P4, P5, P6, P7, P8, P9) -> Boolean
): TestCase {
    val testCase = createTestCaseForCurrentSuite(
        Proposition.forAll(
            propositionFullName,
            arb1, arb2, arb3, arb4, arb5, arb6, arb7, arb8, arb9,
            times, test
        )
    )
    addTestCaseToCurrentSuite(testCase)
    return testCase
}
