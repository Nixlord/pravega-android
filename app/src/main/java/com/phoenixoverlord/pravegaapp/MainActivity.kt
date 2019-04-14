package com.phoenixoverlord.pravegaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.phoenixoverlord.pravega.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toast("Hello World")
    }
}
