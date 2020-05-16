@file:JvmName("FragmentUtils")
package com.phoenixoverlord.pravega.framework.extensions

import androidx.fragment.app.Fragment

fun Fragment.getSimpleName() : String {
    return this.javaClass.simpleName
}
