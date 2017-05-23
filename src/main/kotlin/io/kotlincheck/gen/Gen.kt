package io.kotlincheck.gen

interface Gen<out T> {
    fun generate(): T
}
