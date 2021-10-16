package io.kotlincheck.gen

interface Gen<T> {
    fun generate(): T
    fun isAcceptable(value: T): Boolean = false
}
