package com.phoenixoverlord.pravega.extensions

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.phoenixoverlord.pravega.framework.extensions.getSimpleName
import com.phoenixoverlord.pravega.framework.extensions.getTag


//Using Base Class Throwable instead of granular usage. Less resilient
fun AppCompatActivity.logError(error : Throwable) { logError(getTag(), error) }
fun logError(error : Throwable) { logError("GlobalLog", error) }
fun logError(tag : String = "GlobalLog", exception : Throwable) {

    exception.printStackTrace()

    val text = exception.message ?: "NullErrorMessage"

    Log.e(tag, text)
//    Crashlytics.log(Log.ERROR, "F:$tag", text)
}


fun AppCompatActivity.logDebug(message: String?) { logDebug(getTag(), message) }
fun Fragment.logDebug(message: String?) {
    val name = getSimpleName()
    val length = Math.min(name.length - 1, 20)
    logDebug(name.substring(0..length), message)
}
fun logDebug(tag : String = "GlobalLog", message: String?) {

    val text = message ?: "NullMessage"

    Log.d(tag, text)
//    Crashlytics.log(Log.DEBUG, "F:$tag", text)
}

