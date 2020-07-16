package com.phoenixoverlord.pravegaapp.chat

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.user_chat_item.view.*

abstract class ChatViewHolder<in T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}

class UserMessageViewHolder(view: View) : ChatViewHolder<ChatItem>(view) {
    private val messageContent = view.message

    override fun bind(item: ChatItem) {
        messageContent.text = item.content
    }
}

class OperatorMessageViewHolder(view: View) : ChatViewHolder<ChatItem>(view) {
    private val messageContent = view.message

    override fun bind(item: ChatItem) {
        messageContent.text = item.content
    }
}