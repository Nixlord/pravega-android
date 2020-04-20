package com.phoenixoverlord.pravega.base

import android.content.Intent
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 * Needs a mechanism to inject activity
 * Needs common functions like activityResults and permissionResults handled by common utils
 */

// Common functions here.
interface Component: DefaultLifecycleObserver {}

interface UsesActivityResult {
    fun onActivityResult(owner: LifecycleOwner, success: Boolean, data: Intent?)
}

interface UsesPermission {
    fun onPermissionResult(owner: LifecycleOwner, success: Boolean)
}

class CameraModule: Component,
    UsesPermission,
    UsesActivityResult {
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
