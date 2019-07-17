package pl.kremblewski.android.params

import androidx.fragment.app.Fragment

interface FragmentParams<F : Fragment, P> {
    fun F.loadParams() = lazy { arguments.loadParams<P>() }

    fun F.loadParams(defaultValue: P) = lazy { arguments.loadParams(defaultValue) }

    fun getFragmentClass(): Class<F>

    fun newInstance(params: P): F {
        val fragment = getFragmentClass().newInstance()
        fragment.arguments = createBundleWithParams(params)
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