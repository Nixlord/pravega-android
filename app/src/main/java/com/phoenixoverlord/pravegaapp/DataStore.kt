package com.phoenixoverlord.pravegaapp

import androidx.lifecycle.MutableLiveData

object DataStore {
    class User(val id: String, val name: String, val isAdmin: Boolean)

    val Command = MutableLiveData("What's your name?")
}