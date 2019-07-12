package pl.kremblewski.android.params

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable

private const val PARAMS_KEY = "params"

interface ActivityParams<A : Activity, P> {
    fun A.loadParams(): Lazy<P> {
        return lazy {
            @Suppress("UNCHECKED_CAST")
            intent.extras?.get(PARAMS_KEY) as P
        }
    }

    fun getActivityClass(): Class<A>

    fun createIntent(context: Context, params: P): Intent {
        val intent = Intent(context, getActivityClass())
        when (params) {
            is String -> intent.putExtra(PARAMS_KEY, params)
            is Int -> intent.putExtra(PARAMS_KEY, params)
            is Short -> intent.putExtra(PARAMS_KEY, params)
            is Long -> intent.putExtra(PARAMS_KEY, params)
            is Byte -> intent.putExtra(PARAMS_KEY, params)
            is ByteArray -> intent.putExtra(PARAMS_KEY, params)
            is Char -> intent.putExtra(PARAMS_KEY, params)
            is CharArray -> intent.putExtra(PARAMS_KEY, params)
            is CharSequence -> intent.putExtra(PARAMS_KEY, params)
            is Float -> intent.putExtra(PARAMS_KEY, params)
            is Bundle -> intent.putExtra(PARAMS_KEY, params)
            is Parcelable -> intent.putExtra(PARAMS_KEY, params)
            is Serializable -> intent.putExtra(PARAMS_KEY, params)
        }
        return intent
    }
}

inline fun <reified A : Activity, P> activityParams(): ActivityParams<A, P> {
    return object : ActivityParams<A, P> {
        override fun getActivityClass(): Class<A> {
            return A::class.java
        }
    }
}