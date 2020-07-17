package com.phoenixoverlord.pravegaapp.assistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.phoenixoverlord.pravega.api.core.friend.Friend
import com.phoenixoverlord.pravega.toast
import com.phoenixoverlord.pravega.views.recyclerview.PravegaAdapter
import com.phoenixoverlord.pravega.views.recyclerview.PravegaRecyclerView
import com.phoenixoverlord.pravegaapp.DataStore
import com.phoenixoverlord.pravegaapp.R
import kotlinx.android.synthetic.main.action_list_item.view.*
import kotlinx.android.synthetic.main.activity_assistant.*
import kotlinx.android.synthetic.main.list_item.*

class AssistantActivity : AppCompatActivity() {
    private lateinit var adapter: PravegaAdapter<String>

    private fun setupRecyclerView() {
        val binder: ((View, String) -> Unit) = { view, string ->
            view.apply {
                actionText.text = string
            }
        }
        adapter = assistantActions.attach(
            R.layout.action_list_item,
            LinearLayoutManager(this@AssistantActivity, RecyclerView.VERTICAL, false),
            binder
        )
        adapter.addModels(arrayListOf(
            "Personal Assistant",
            "Customer Service",
            "Rock Paper Scissors"
        ))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assistant)
        setupRecyclerView()
    }
}
