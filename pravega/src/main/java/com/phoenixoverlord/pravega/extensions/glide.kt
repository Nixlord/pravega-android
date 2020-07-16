package com.phoenixoverlord.pravega.extensions

import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.phoenixoverlord.pravega.framework.BaseActivity
import com.phoenixoverlord.pravega.cloud.firebase.extensions.Firebase.storage
import java.io.File
import java.lang.Error

fun BaseActivity.loadImage(imageView: ImageView, imageName : String) : ImageView {
    val storageReference = storage.child("images").child(imageName)
    return Glide.with(this)
        .load(storageReference)
        .into(imageView)
        .view
}

fun BaseActivity.loadCircularImage(imageView: ImageView, imageName : String) : ImageView {
    val storageReference = storage.child("images").child(imageName)
    return Glide.with(this)
        .load(storageReference)
        .apply(RequestOptions.circleCropTransform())
        .into(imageView)
        .view
}


fun BaseActivity.loadImage(imageView: ImageView, imageFile : File) : ImageView {
    return Glide.with(this).load(imageFile).into(imageView).view
}

fun BaseActivity.loadImage(imageView: ImageView, @DrawableRes drawableID : Int) : ImageView {
    return Glide.with(this)
        .load(drawableID)
        .apply(RequestOptions()
            .fitCenter()
            .format(DecodeFormat.PREFER_ARGB_8888)
            .override(Target.SIZE_ORIGINAL))
        .into(imageView).view
}

fun BaseActivity.downloadImage(imageName: String, onSuccess : (file : File) -> Unit) {
    val storageReference = storage.child("images/$imageName")
    Glide.with(this)
        .asFile()
        .load(storageReference)
        .listener(object : RequestListener<File> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<File>?,
                isFirstResource: Boolean
            ): Boolean {
                logError(Error("LoadFailed"))
                return false
            }

            override fun onResourceReady(
                resource: File?,
                model: Any?,
                target: Target<File>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                if (resource != null)
                    onSuccess(resource)
                else
                    logError(Error("Resource Null"))

                return true
            }
        })
        .submit()
}

fun BaseActivity.getExternallyAccessibleURI(file : File) : Uri {
    return FileProvider.getUriForFile(
        this,
        "com.phoenixoverlord.fileprovider",
        file
    )
}