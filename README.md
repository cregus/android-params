# Android Params [![](https://jitpack.io/v/cregus/android-params.svg)](https://jitpack.io/#cregus/android-params)
Android library to simplify passing params to activities and fragments (currently it's working only with the androidx fragments).

## Setup
### Add the JitPack repository to your build file
```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

### Add library dependency to your project build file
```
dependencies {
  implementation 'com.github.cregus:android-params:1.0'
}
```

## Sample usage
### Passing params to activity
```kotlin
class UserActivity : AppCompatActivity() {
    companion object : ActivityParams<UserActivity, Long> by activityParams()

    private val id by loadParams()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("Params", "id: $id")
    }
}
```
and then:
```kotlin
val intent = UserActivity.createIntent(context, 1)
```

### Passing params to fragment
```kotlin
class UserFragment : Fragment() {
    companion object : FragmentParams<UserFragment, Long> by fragmentParams()

    private val id by loadParams()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("Params", "id: $id")
    }
}
```
and then:
```kotlin
val fragment = UserFragment.newInstance(1)
```

### Passing multiple params
```kotlin
class UserFragment : Fragment() {
    @Parcelize
    data class Params(val id: Long, val name: String) : Parcelable

    companion object : FragmentParams<UserFragment, Params> by fragmentParams()

    private val params by loadParams()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("Params", "id: ${params.id}, name: ${params.name}")
    }
}
```
and then:
```kotlin
val fragment = UserFragment.newInstance(UserFragment.Params(1, "John Smith"))
```