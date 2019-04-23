@file:JvmName("FragmentUtils")
package com.phoenixoverlord.pravega.extensions

import androidx.fragment.app.Fragment

fun Fragment.getSimpleName() : String {
    return this.javaClass.simpleName
}
