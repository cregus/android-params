package pl.kremblewski.android.params

import android.app.Activity
import android.content.Context
import android.content.Intent

interface ActivityParams<A : Activity, P> {
    fun A.loadParams() = lazy { intent.extras.loadParams<P>() }

    fun A.loadParams(defaultValue: P) = lazy { intent.extras.loadParams(defaultValue) }

    fun getActivityClass(): Class<A>

    fun createIntent(context: Context, params: P): Intent {
        val intent = Intent(context, getActivityClass())
        intent.replaceExtras(createBundleWithParams(params))
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