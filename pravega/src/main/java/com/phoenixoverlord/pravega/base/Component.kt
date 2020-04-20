package com.phoenixoverlord.pravega.base

import android.content.Intent
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.google.android.gms.tasks.OnFailureListener
import java.io.File
import java.util.concurrent.ConcurrentLinkedQueue

/**
 * Needs a mechanism to inject activity
 * Needs common functions like activityResults and permissionResults handled by common utils
 */

// Common functions here.
abstract class Component: DefaultLifecycleObserver {}

interface UsesActivityResult {
    fun onActivityResult(owner: LifecycleOwner, success: Boolean, data: Intent?)
}

interface UsesPermission {
    fun onPermissionResult(owner: LifecycleOwner, success: Boolean)
}

// Need proper mechanism for reentrant calls
class CameraModule: Component(),
    UsesPermission,
    UsesActivityResult {

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

    fun takePhoto(params: String): Result {
        val result = Result()
        taskQueue.add(result)
        return result
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
    }

    override fun onActivityResult(owner: LifecycleOwner, success: Boolean, data: Intent?) {
        TODO("Not yet implemented")
    }

    override fun onPermissionResult(owner: LifecycleOwner, success: Boolean) {
        TODO("Not yet implemented")
    }
}
