package com.phoenixoverlord.pravega.utils

import android.net.Uri
import androidx.core.content.FileProvider

public class StupidAndroidFrameworkFileProvider : FileProvider() {
    override fun getType(uri: Uri): String {
        return "images/jpeg"
    }
}