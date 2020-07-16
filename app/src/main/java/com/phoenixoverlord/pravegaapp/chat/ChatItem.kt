package com.phoenixoverlord.pravegaapp.chat

class ChatItem(
    val content: String,
    val messageType: Int
) {
    companion object {
        const val TYPE_USER_MESSAGE = 0
        const val TYPE_OPERATOR_MESSAGE = 1
    }
}