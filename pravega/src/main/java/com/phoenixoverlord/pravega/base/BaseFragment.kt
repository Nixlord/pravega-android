package com.phoenixoverlord.pravega.base

import androidx.fragment.app.Fragment


abstract class BaseFragment : Fragment() {
    protected val base : BaseActivity by lazy { activity as BaseActivity }
}