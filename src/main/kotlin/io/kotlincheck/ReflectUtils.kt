package io.kotlincheck

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.lang.reflect.WildcardType

interface TypeParameterProvider<out T> {
    fun getNthTypeParameter(index: Int): Type {
        val parameterizedType1 = javaClass.genericInterfaces.first() as ParameterizedType
        val parameterizedType2 = parameterizedType1.actualTypeArguments.first() as ParameterizedType
        val type = parameterizedType2.actualTypeArguments[index]
        return if (type is WildcardType) type.upperBounds.first() else type
    }
}
