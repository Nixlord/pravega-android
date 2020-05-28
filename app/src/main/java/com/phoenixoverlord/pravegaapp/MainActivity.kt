package com.phoenixoverlord.pravegaapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.phoenixoverlord.pravega.Pravega
import com.phoenixoverlord.pravega.api.PravegaService
import com.phoenixoverlord.pravega.api.core.country.Country
import com.phoenixoverlord.pravega.cloud.firebase.remoteConfig
import com.phoenixoverlord.pravega.extensions.logDebug
import com.phoenixoverlord.pravega.extensions.logError
import com.phoenixoverlord.pravega.toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.view.*
import java.io.IOException
//import javax.websocket.*

class MainActivity : AppCompatActivity() {

    val pravega = PravegaService(Pravega.DEV)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //toast("Hello World")
        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show()

        //addTestDataToRecyclerView()
        addCountryDataToRecyclerView()

        remoteConfig(mapOf(
            "predictor" to "https://pravegapredictor.herokuapp.com/",
            "backend" to "https://pravegacore.herokuapp.com/"
        )).thenApply {
            it.entries.forEach { logDebug("${it.key}:${it.value}") }
        }

        mainFab.setOnClickListener {
            toast(Pravega.DEV.toString())
            pravega.friendAPI.getAllFriends()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { values, err ->
                    if (err != null) {
                        logError(err)
                    }
                    else {
                        values.forEach { (idx, friend) ->
                            logDebug("$idx: $friend")
                        }
                    }

                }
        }

        /*val ws = object: Endpoint() {
            override fun onOpen(session: Session?, config: EndpointConfig?) {
                val remote = session!!.basicRemote
                session.addMessageHandler(object : MessageHandler.Whole<String?> {
                    override fun onMessage(text: String?) {
                        try {
                            remote.sendText(text)
                        } catch (ioe: IOException) {
                            // handle send failure here
                        }
                    }
                })
            }

            override fun onClose(session: Session?, closeReason: CloseReason?) {
                super.onClose(session, closeReason)
            }

            override fun onError(session: Session?, thr: Throwable?) {
                super.onError(session, thr)
            }
        } */
    }

    private fun addCountryDataToRecyclerView() {
        val binder: ((View, Country) -> Unit) = { view, item ->
            view.apply {
                textView.text = item.name
                textView2.text = item.capital
                button.setOnClickListener {
                    if(textView3.visibility == View.VISIBLE)
                        textView3.visibility = View.GONE
                    else {
                        textView3.visibility = View.VISIBLE
                        textView3.text = item.region
                    }
                }
            }
        }

        var list : ArrayList<Country>
        val disposable = pravega.countryAPI.getAllCountries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { value, err ->
                if (err != null) {
                    logError(err)
                }
                else {
                    list = ArrayList(value)
                    recyclerview.attach(
                        list = list,
                        layout = R.layout.list_item,
                        layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false),
                        binder = binder
                    )
                }
            }
    }

    private fun addTestDataToRecyclerView() {
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
