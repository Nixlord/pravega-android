package com.phoenixoverlord.pravega.framework

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

//class Test: PravegaActivity() {
//    private val cameraModule = CameraModule(this)
//    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
//        super.onCreate(savedInstanceState, persistentState)
//        inject(cameraModule)
//
//        val textView = TextView(this)
//        textView.setOnClickListener {
//            cameraModule.takePhoto {
//                Log.d("FILE Recieved", it.name)
//            }
//        }
//    }
//
//}


open class PravegaActivity: AppCompatActivity() {
    // Choose between BaseActivity Approach and NestedComponent approach
    private val activityResultComponents = arrayListOf<UsesActivityResult>()
    private val permissionResultComponents = arrayListOf<UsesPermission>()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

    }

    fun inject(vararg components: Component) {
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
        activityResultComponents.forEach { component ->
            component.onActivityResult(this, true, data)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionResultComponents.forEach { component ->
            component.onPermissionResult(this, true)
        }
    }
}
