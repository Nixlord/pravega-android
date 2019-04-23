package com.phoenixoverlord.pravega.extensions

import android.os.Environment
import com.phoenixoverlord.pravega.utils.uniqueName
import java.io.File


fun getDefaultFolder() : File {
    val destinationRoot = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
    return File(destinationRoot, "AppPictures")
}

fun createImageFile(fileName : String = uniqueName(), parentFolder : File = getDefaultFolder()) : File {
    return File(parentFolder, fileName)
}