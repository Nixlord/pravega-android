package com.phoenixoverlord.pravega.framework

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.phoenixoverlord.pravega.Pravega
import com.phoenixoverlord.pravega.api.PravegaService
import com.phoenixoverlord.pravega.api.core.country.Country
import com.phoenixoverlord.pravega.di.DaggerPravegaComponent
import com.phoenixoverlord.pravega.extensions.logError
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

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

    @Inject
    lateinit var pravega : PravegaService

    val daggerComponent = DaggerPravegaComponent.builder().pravegaService(PravegaService(Pravega.DEV)).build()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        daggerComponent.inject(this)
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

    fun countryData() : ArrayList<Country>? {
        var list : ArrayList<Country>? = null
        val disposable = pravega
            .countryApi(pravega.createAdapter("https://restcountries-v1.p.rapidapi.com"))
            .getAllCountries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { value, err ->
                if (err != null) {
                    logError(err)
                }
                else {
                    list = ArrayList(value)
                }
            }
        return list
    }
}
