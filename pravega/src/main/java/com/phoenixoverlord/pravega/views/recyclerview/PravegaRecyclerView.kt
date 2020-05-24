package com.phoenixoverlord.pravega.views.recyclerview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class PravegaRecyclerView : RecyclerView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun<Model> attach(
        layout : Int,
        layoutManager: LayoutManager,
        binder: (View, Model) -> Unit
    ): PravegaAdapter<Model> {
        this.layoutManager = layoutManager
        val pravegaAdapter =
            PravegaAdapter(
                layout,
                binder
            )
        this.adapter = pravegaAdapter
        return pravegaAdapter
    }
}


