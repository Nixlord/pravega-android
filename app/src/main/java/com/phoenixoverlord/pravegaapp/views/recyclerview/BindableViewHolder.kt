package com.phoenixoverlord.pravegaapp.views.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BindableViewHolder<Model>(itemView : View) : RecyclerView.ViewHolder(itemView) {
    fun bindModelToView(model: Model, bind : (View, Model) -> Unit) {
        // DEFAULT IMPLEMENTATION
        bind(itemView, model)
    }
}