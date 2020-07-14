package com.phoenixoverlord.pravegaapp.assistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.phoenixoverlord.pravegaapp.DataStore
import com.phoenixoverlord.pravegaapp.R
import kotlinx.android.synthetic.main.activity_assistant.*

class AssistantActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assistant)

        buttonSendCommand.setOnClickListener {
            DataStore.Command.value = "What's your name?"
        }
    }
}
