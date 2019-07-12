package pl.kremblewski.android.params

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import java.io.Serializable

private const val PARAMS_KEY = "params"

interface FragmentParams<F : Fragment, P> {
    fun F.loadParams(): Lazy<P> {
        return lazy {
            @Suppress("UNCHECKED_CAST")
            arguments?.get(PARAMS_KEY) as P
        }
    }

    fun getFragmentClass(): Class<F>

    fun newInstance(params: P): F {
        val fragment = getFragmentClass().newInstance()
        val args = Bundle()
        when (params) {
            is String -> args.putString(PARAMS_KEY, params)
            is Int -> args.putInt(PARAMS_KEY, params)
            is Short -> args.putShort(PARAMS_KEY, params)
            is Long -> args.putLong(PARAMS_KEY, params)
            is Byte -> args.putByte(PARAMS_KEY, params)
            is ByteArray -> args.putByteArray(PARAMS_KEY, params)
            is Char -> args.putChar(PARAMS_KEY, params)
            is CharArray -> args.putCharArray(PARAMS_KEY, params)
            is CharSequence -> args.putCharSequence(PARAMS_KEY, params)
            is Float -> args.putFloat(PARAMS_KEY, params)
            is Bundle -> args.putBundle(PARAMS_KEY, params)
            is Parcelable -> args.putParcelable(PARAMS_KEY, params)
            is Serializable -> args.putSerializable(PARAMS_KEY, params)
        }
        fragment.arguments = args
        return fragment
    }
}

inline fun <reified F : Fragment, P> fragmentParams(): FragmentParams<F, P> {
    return object : FragmentParams<F, P> {
        override fun getFragmentClass(): Class<F> {
            return F::class.java
        }
    }
}