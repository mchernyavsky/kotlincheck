package io.kotlincheck

import io.kotlintest.KTestJUnitRunner
import io.kotlintest.Spec
import io.kotlintest.TestCase
import io.kotlintest.TestSuite
import org.junit.runner.RunWith

@RunWith(KTestJUnitRunner::class) // required to let IntelliJ discover tests
abstract class PropSpec(body: PropSpec.() -> Unit = {}) : Spec() {
    private var currentSuite = rootTestSuite
    private var propositionNum = 0

    init {
        body()
    }

    fun property(name: String, init: () -> Unit): Unit {
        val suite = TestSuite("Property: $name")
        currentSuite.addNestedSuite(suite)
        val temp = currentSuite
        currentSuite = suite
        propositionNum = 1
        init()
        currentSuite = temp
    }

    fun createTestCaseForCurrentSuite(test: () -> Unit): TestCase {
        return TestCase(currentSuite, "Proposition #${propositionNum++}", test, defaultTestCaseConfig)
    }

    fun addTestCaseToCurrentSuite(test: TestCase) {
        currentSuite.addTestCase(test)
    }
}
