package com.phoenixoverlord.pravegaapp.chat

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.phoenixoverlord.pravegaapp.R
import com.phoenixoverlord.pravegaapp.chat.ChatItem.Companion.TYPE_OPERATOR_MESSAGE
import com.phoenixoverlord.pravegaapp.chat.ChatItem.Companion.TYPE_USER_MESSAGE

class ChatAdapter(
    private val data: MutableList<ChatItem>
) : RecyclerView.Adapter<ChatViewHolder<*>>() {

    fun addChatItem(chatItem: ChatItem) {
        data.add(0, chatItem)
        notifyItemInserted(0)
    }

    private lateinit var layoutInflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder<*> {

        if (!::layoutInflater.isInitialized)
            layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            TYPE_USER_MESSAGE -> {
                val view = layoutInflater.inflate(R.layout.user_chat_item, parent, false)
                UserMessageViewHolder(view)
            }
            TYPE_OPERATOR_MESSAGE -> {
                val view = layoutInflater.inflate(R.layout.operator_chat_item, parent, false)
                OperatorMessageViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: ChatViewHolder<*>, position: Int) {
        val item = data[position]
        Log.d("ChatAdapter", position.toString() + item.content)
        when (holder) {
            is UserMessageViewHolder -> holder.bind(item)
            is OperatorMessageViewHolder -> holder.bind(item)
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int = data[position].messageType
}