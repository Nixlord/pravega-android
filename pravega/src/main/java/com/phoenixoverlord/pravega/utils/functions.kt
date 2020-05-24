@file:JvmName("Utils")
package com.phoenixoverlord.pravega.utils

import com.phoenixoverlord.pravega.cloud.firebase.extensions.Firebase.auth
import java.text.SimpleDateFormat
import java.util.*

fun timeStamp() = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())

val loopingAtomicInteger = LoopingAtomicInteger(0, 10000)

fun uniqueName() = "${timeStamp()}_${auth.currentUser?.uid.hashCode()}_${loopingAtomicInteger.nextInt()}"