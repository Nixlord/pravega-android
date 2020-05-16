package com.phoenixoverlord.pravega.framework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.phoenixoverlord.pravega.mechanisms.ActivityResultHandler
import com.phoenixoverlord.pravega.mechanisms.NotificationModule
import com.phoenixoverlord.pravega.mechanisms.PermissionsModule
import com.phoenixoverlord.pravega.utils.LoopingAtomicInteger
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


// Rewrite this Activity to have better function injection. Maybe a map(activityCallback -> customHook)
abstract class BaseActivity : AppCompatActivity() {

    protected val compositeDisposable = CompositeDisposable()
    private val loopingAtomicInteger = LoopingAtomicInteger(100, 10000)
    private val activityResultHandler = ActivityResultHandler()
    // Improve notificationModule later, implement lifecycle
    val notificationModule : NotificationModule by lazy { NotificationModule(this) }
    private val permissionsModule = PermissionsModule()

    fun rx(disposableFn: (() -> Disposable)) {
        compositeDisposable.add(disposableFn())
    }

//    Camera API has changed. Need to rewrite
//    private val camera = CameraModule()
//    /** CameraModule */
//    fun takePhoto(prompt : String) = camera.takePhoto(this, prompt)

    /** ActivityResultModule */
    fun startActivityGetResult(
        intent : Intent,
        requestCode : Int = loopingAtomicInteger.nextInt()
    ) = activityResultHandler
        .createAction(requestCode)
        .perform {
            startActivityForResult(intent, requestCode)
        }

    /** PermissionsModule */
    fun withPermissions(vararg permissions : String) =
        permissionsModule.PermissionBuilder(loopingAtomicInteger.nextInt())
            .withPermissions(this, permissions.toCollection(ArrayList()))

    /** CompressionModule */
//    fun compressImage(image : File) = compressionModule.compressImage(this, image)


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        activityResultHandler.onActivityResult(requestCode, resultCode, data)
        //    Camera API has changed. Need to rewrite
        //    camera.internalOnActivityResult(this, requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionsModule.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

}