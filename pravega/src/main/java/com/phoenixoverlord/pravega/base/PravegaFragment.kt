package com.phoenixoverlord.pravega.base

import androidx.fragment.app.Fragment


abstract class PravegaFragment : Fragment() {
    protected val base : PravegaActivity by lazy { activity as PravegaActivity }
}