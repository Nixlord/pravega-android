package com.phoenixoverlord.pravega.framework

import android.content.Intent
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import java.io.File
import java.util.concurrent.ConcurrentLinkedQueue

/**
 * Needs a mechanism to inject activity
 * Needs common functions like activityResults and permissionResults handled by common utils
 */

// Common functions here.
// Implement multiple constructors taking PravegaActivity / PravegaFragment
// Common utility to execute with Activity / Fragment
// How to use nested components ?
/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
abstract class Component: DefaultLifecycleObserver {}

interface UsesActivityResult {
    fun onActivityResult(owner: LifecycleOwner, success: Boolean, data: Intent?)
}

interface UsesPermission {
    fun onPermissionResult(owner: LifecycleOwner, success: Boolean)
}

// Need proper mechanism for reentrant calls
class CameraModule(val activity: PravegaActivity): Component(),
    UsesPermission,
    UsesActivityResult {

    // Make this into a generic interface
    class Result(
        var onSuccess: (File) -> Unit = {},
        var onFailure: (Error) -> Unit = {}
    ) {
        fun addOnSuccessListener(onSuccess: (File) -> Unit) {
            this.onSuccess = onSuccess
        }

        fun addonFailureListener(onFailure: (Error) -> Unit) {
            this.onFailure = onFailure
        }
    }
    val taskQueue = ConcurrentLinkedQueue<Result>()

    fun takePhoto(params: () -> Int): Result {
        val result = Result()
        taskQueue.add(result)
//        activity.requestPermissions()
        return result
    }


    override fun onActivityResult(owner: LifecycleOwner, success: Boolean, data: Intent?) {
        TODO("Not yet implemented")
    }

    override fun onPermissionResult(owner: LifecycleOwner, success: Boolean) {
        TODO("Not yet implemented")
    }
}
