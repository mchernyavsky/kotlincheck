package io.kotlincheck

import java.io.Serializable

internal data class Tuple2<out T1, out T2>(
        val elem1: T1,
        val elem2: T2
) : Serializable {
    override fun toString(): String = "($elem1, $elem2)"
}

internal data class Tuple3<out T1, out T2, out T3>(
        val elem1: T1,
        val elem2: T2,
        val elem3: T3
) : Serializable {
    override fun toString(): String = "($elem1, $elem2, $elem3)"
}

internal data class Tuple4<out T1, out T2, out T3, out T4>(
        val elem1: T1,
        val elem2: T2,
        val elem3: T3,
        val elem4: T4
) : Serializable {
    override fun toString(): String = "($elem1, $elem2, $elem3, $elem4)"
}

internal data class Tuple5<out T1, out T2, out T3, out T4, out T5>(
        val elem1: T1,
        val elem2: T2,
        val elem3: T3,
        val elem4: T4,
        val elem5: T5
) : Serializable {
    override fun toString(): String = "($elem1, $elem2, $elem3, $elem4, $elem5)"
}

internal data class Tuple6<out T1, out T2, out T3, out T4, out T5, out T6>(
        val elem1: T1,
        val elem2: T2,
        val elem3: T3,
        val elem4: T4,
        val elem5: T5,
        val elem6: T6
) : Serializable {
    override fun toString(): String = "($elem1, $elem2, $elem3, $elem4, $elem5, $elem6)"
}

internal data class Tuple7<out T1, out T2, out T3, out T4, out T5, out T6, out T7>(
        val elem1: T1,
        val elem2: T2,
        val elem3: T3,
        val elem4: T4,
        val elem5: T5,
        val elem6: T6,
        val elem7: T7
) : Serializable {
    override fun toString(): String = "($elem1, $elem2, $elem3, $elem4, $elem5, $elem6, $elem7)"
}

internal data class Tuple8<out T1, out T2, out T3, out T4, out T5, out T6, out T7, out T8>(
        val elem1: T1,
        val elem2: T2,
        val elem3: T3,
        val elem4: T4,
        val elem5: T5,
        val elem6: T6,
        val elem7: T7,
        val elem8: T8
) : Serializable {
    override fun toString(): String = "($elem1, $elem2, $elem3, $elem4, $elem5, $elem6, $elem7, $elem8)"
}

internal data class Tuple9<out T1, out T2, out T3, out T4, out T5, out T6, out T7, out T8, out T9>(
        val elem1: T1,
        val elem2: T2,
        val elem3: T3,
        val elem4: T4,
        val elem5: T5,
        val elem6: T6,
        val elem7: T7,
        val elem8: T8,
        val elem9: T9
) : Serializable {
    override fun toString(): String =
            "($elem1, $elem2, $elem3, $elem4, $elem5, $elem6, $elem7, $elem8, $elem9)"
}
