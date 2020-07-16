package com.phoenixoverlord.pravegaapp.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.phoenixoverlord.pravega.extensions.loadImage
import com.phoenixoverlord.pravega.framework.PravegaFragment
import com.phoenixoverlord.pravega.views.extensions.inflate
import com.phoenixoverlord.pravegaapp.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomePage: PravegaFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    var index = 0
    val images = arrayOf(R.drawable.home_page, R.drawable.list_page, R.drawable.pdp_page, R.drawable.cart_page_first, R.drawable.cart_page_select, R.drawable.filter_page)

    fun loadImage() {
        host.loadImage(homeImageView, images[index])
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadImage()
        homeImageView.setOnClickListener {
            index = (index + 1) % images.size
            loadImage()
        }
    }
}