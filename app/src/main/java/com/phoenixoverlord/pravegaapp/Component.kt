package com.phoenixoverlord.pravegaapp

import android.content.Intent
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 * Needs a mechanism to inject activity
 * Needs common functions like activityResults and permissionResults handled by common utils
 */

// Use subclasses for each type (activityResult, permissionResult) Common types in Component.
interface ActivityResult {
    fun onActivityResult(owner: LifecycleOwner, success: Boolean, data: Intent?)
}

interface PermissionResult {
    fun onPermissionResult(owner: LifecycleOwner, success: Boolean)
}


abstract class Component: DefaultLifecycleObserver {
    // Remove dependency on requestCode
    open fun onActivityResult(owner: LifecycleOwner, requestCode: Int, resultCode: Int, data: Intent?) {}
    // Same
    open fun onRequestPermissionsResult(owner: LifecycleOwner, requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {}
}

object CameraModule: Component() {
    override fun onActivityResult(
        owner: LifecycleOwner,
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(owner, requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(
        owner: LifecycleOwner,
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(owner, requestCode, permissions, grantResults)
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
}
