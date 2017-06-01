package io.kotlincheck

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.lang.reflect.WildcardType

interface TypeParameterProvider<out T> {
    fun getNthTypeParameter(index: Int): Type {
        val parametrizedType1 = javaClass.genericInterfaces.first() as ParameterizedType
        val parametrizedType2 = parametrizedType1.actualTypeArguments.first() as ParameterizedType
        val type = parametrizedType2.actualTypeArguments[index]
        return if (type is WildcardType) type.upperBounds.first() else type
    }

    companion object {
        inline fun <reified T> getNthTypeParameter(index: Int): Type =
                object : TypeParameterProvider<T> {}.getNthTypeParameter(index)
    }
}
