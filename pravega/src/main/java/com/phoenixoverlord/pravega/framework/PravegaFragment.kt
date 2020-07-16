package com.phoenixoverlord.pravega.framework

import android.content.Intent
import androidx.fragment.app.Fragment


// How to keep common code at one place ?
abstract class PravegaFragment : Fragment() {
    protected val host : BaseActivity by lazy { activity as BaseActivity }
//
//    private val activityResultComponents = arrayListOf<UsesActivityResult>()
//    private val permissionResultComponents = arrayListOf<UsesPermission>()
//
//
//    fun inject(vararg components: Component) {
//        components.forEach { inject(it) }
//    }
//
//    fun inject(component: Component) {
//        lifecycle.addObserver(component)
//        if (component is UsesActivityResult){
//            activityResultComponents.add(component)
//        }
//
//        if (component is UsesPermission) {
//            permissionResultComponents.add(component)
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        activityResultComponents.forEach { component ->
//            component.onActivityResult(this, true, data)
//        }
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        permissionResultComponents.forEach { component ->
//            component.onPermissionResult(this, true)
//        }
//    }
}