package com.phoenixoverlord.pravega

import android.content.Context
import android.widget.Toast

object Notify {
    fun shortToast(context: Context, message : String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}