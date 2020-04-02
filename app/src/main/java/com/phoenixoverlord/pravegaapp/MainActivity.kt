package com.phoenixoverlord.pravegaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.phoenixoverlord.pravega.extensions.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.*
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.android.synthetic.main.list_item.view.button
import java.util.*
import java.util.function.Consumer

//import com.phoenixoverlord.pravega.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //toast("Hello World")

        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show()

//        recyclerview.apply {
//            recyclerViewModule.create<String>(view -> {
//            view.appl
//        })
//        }
//
//
        val list = arrayListOf("Shibasis", "Diksha")

        val binder: ((View, String) -> Unit) = { view, item ->
            view.apply {
                textView.text = item
                textView2.text = item
                button.setOnClickListener {
                    recyclerview.addModel("Shweta")
                    Toast.makeText(this@MainActivity,"Working",  Toast.LENGTH_SHORT).show()
                }
            }
        }

        recyclerview.attach(
            list,
            R.layout.list_item,
            LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false),
            binder
        )

        recyclerview.addModel("Soubhagya")
        recyclerview.removeModel("Diksha")
        recyclerview.addModel("Parichay")


    }
}
