@file:JvmName("ActivityUtils")
package com.phoenixoverlord.pravega.framework.extensions

import android.content.Intent
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import es.dmoral.toasty.Toasty

fun AppCompatActivity.getSimpleName() : String {
    return this.javaClass.simpleName
}

fun AppCompatActivity.getTag() : String {
    val length = this.getSimpleName().length
    val till = Math.min(length - 1, 20)
    return this.getSimpleName().substring(0..till)
}

fun AppCompatActivity.toastSuccess(message : String) {
    Toasty.success(this, message, Toast.LENGTH_SHORT, true).show()
}

fun AppCompatActivity.toastError(message : String) {
    Toasty.error(this, message, Toast.LENGTH_SHORT, true).show()
}

fun AppCompatActivity.finishAndStart(activity : Class<*>) {
    startActivity(Intent(this, activity))
    finish()
}

fun AppCompatActivity.replaceFragment(containerID : Int, fragment : Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(containerID, fragment)
        .commit()
}

fun AppCompatActivity.addFragment(containerID : Int, fragment : Fragment) {
    supportFragmentManager.beginTransaction()
        .add(containerID, fragment)
        .commit()
}

fun AppCompatActivity.safeIntentDispatch(intent : Intent) {
    intent.resolveActivity(packageManager)?.let {
        startActivity(intent)
    }
}

fun PackageManager.intentHandlerExists(intent : Intent) = intent.resolveActivity(this) != null