package com.phoenixoverlord.pravegaapp.views.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Add Defensive Code, then make this support multiple types of data derived from a base class and sealed classes.
 * Read source to understand how recyclerView works
 */
class PravegaAdapter<Model>(
    private val layout : Int,
    private val binder : (View, Model) -> Unit
): RecyclerView.Adapter<BindableViewHolder<Model>>() {

    private val list = arrayListOf<Model>()

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    fun addModel(model : Model) {
        val idx = list.size
        list.add(model)
        notifyItemInserted(idx)
    }
    fun addModels(models: Collection<Model>) {
        list.addAll(models)
        notifyItemRangeInserted(list.size, models.size)
    }
    //TODO : This is O(N). Reduce its time.
    // 	android.support.v7.util.DiffUtil
    fun removeModel(model : Model) {
        val idx = list.size - 1
        list.remove(model)
        notifyItemRemoved(idx)
    }
    fun removeModels(models: Collection<Model>) {
        val indices = models.map { list.indexOf(it) }
        list.removeAll(models)
        indices.forEach { notifyItemRemoved(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder<Model> {
        return BindableViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(layout, parent, false)
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: BindableViewHolder<Model>, position: Int) {
        holder.bindModelToView(list[position], binder)
    }
}



