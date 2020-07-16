package com.phoenixoverlord.pravegaapp

import android.os.Bundle
import android.os.PersistableBundle
import com.phoenixoverlord.pravega.framework.BaseActivity
import com.phoenixoverlord.pravega.framework.extensions.addFragment
import com.phoenixoverlord.pravegaapp.screens.HomePage

class MyntraActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myntra)
        addFragment(R.id.fragmentContainer, HomePage())
    }
}