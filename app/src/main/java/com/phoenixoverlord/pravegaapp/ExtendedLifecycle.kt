package com.phoenixoverlord.pravegaapp

import android.content.Intent
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

interface Component: DefaultLifecycleObserver {
    fun onActivityResult(owner: LifecycleOwner, requestCode: Int, resultCode: Int, data: Intent?) {}
    fun onRequestPermissionsResult(owner: LifecycleOwner, requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {}
}

object CameraModule: Component {
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
