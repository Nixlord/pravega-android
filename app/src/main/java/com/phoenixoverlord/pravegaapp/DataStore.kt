package com.phoenixoverlord.pravegaapp

import androidx.lifecycle.MutableLiveData

object Action {
    val GAME = "GAME"
    val EVA = "EVA"
    val CC = "CC"
}

object DataStore {
    val Command = MutableLiveData("What's your name?")
}