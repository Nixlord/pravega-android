package com.phoenixoverlord.pravega.base

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class PravegaActivity: AppCompatActivity() {
    val activityResultComponents = arrayListOf<UsesActivityResult>()
    val permissionResultComponents = arrayListOf<UsesPermission>()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    fun inject(components: ArrayList<Component>) {
        components.forEach { inject(it) }
    }

    fun inject(component: Component) {
        lifecycle.addObserver(component)
        if (component is UsesActivityResult){
            activityResultComponents.add(component)
        }

        if (component is UsesPermission) {
            permissionResultComponents.add(component)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


}