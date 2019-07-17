package pl.kremblewski.android.params

import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable

internal const val PARAMS_KEY = "params"

internal fun <P> Bundle?.loadParams(): P {
    @Suppress("UNCHECKED_CAST")
    return if (this?.containsKey(PARAMS_KEY) == true) {
        get(PARAMS_KEY) as P
    } else {
        throw IllegalStateException("Requested parameter is not defined.")
    }
}

internal fun <P> Bundle?.loadParams(defaultValue: P): P {
    @Suppress("UNCHECKED_CAST")
    return if (this?.containsKey(PARAMS_KEY) == true) {
        get(PARAMS_KEY) as P
    } else {
        defaultValue
    }
}

internal fun <P> Bundle.setParams(params: P) {
    when (params) {
        is String -> putString(PARAMS_KEY, params)
        is Int -> putInt(PARAMS_KEY, params)
        is Short -> putShort(PARAMS_KEY, params)
        is Long -> putLong(PARAMS_KEY, params)
        is Byte -> putByte(PARAMS_KEY, params)
        is ByteArray -> putByteArray(PARAMS_KEY, params)
        is Char -> putChar(PARAMS_KEY, params)
        is CharArray -> putCharArray(PARAMS_KEY, params)
        is CharSequence -> putCharSequence(PARAMS_KEY, params)
        is Float -> putFloat(PARAMS_KEY, params)
        is Bundle -> putBundle(PARAMS_KEY, params)
        is Parcelable -> putParcelable(PARAMS_KEY, params)
        is Serializable -> putSerializable(PARAMS_KEY, params)
    }
}

internal fun <P> createBundleWithParams(params: P): Bundle {
    return Bundle().apply { setParams(params) }
}