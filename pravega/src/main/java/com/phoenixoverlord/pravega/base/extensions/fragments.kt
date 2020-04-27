@file:JvmName("FragmentUtils")
package com.phoenixoverlord.pravega.base.extensions

import androidx.fragment.app.Fragment

fun Fragment.getSimpleName() : String {
    return this.javaClass.simpleName
}
