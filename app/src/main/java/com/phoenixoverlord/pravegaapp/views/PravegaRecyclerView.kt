package com.phoenixoverlord.pravegaapp.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PravegaRecyclerView : RecyclerView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    //learn generics and reified types in kotlin
    fun<Model> addModel(model : Model) {
        (this.adapter as Adapter<Model>).addModel(model)
    }

    fun<Model> removeModel(model : Model) {
        (this.adapter as Adapter<Model>).removeModel(model)
    }


    fun<Model> attach(list : ArrayList<Model>,
                                          layout : Int,
                                          layoutManager: LayoutManager,
                                          binder: (View, Model) -> Unit) {

        this.layoutManager = layoutManager
        this.adapter = Adapter(list, layout, binder)
    }

    /**                                         IMPLEMENTATION                                                 */
    inner class Adapter<Model>(
        private val list : ArrayList<Model>,
        private val layout : Int,
        private val binder : (View, Model) -> Unit
    ): RecyclerView.Adapter<BindableViewHolder<Model>>() {

        fun addModel(model : Model) {
            list.add(model)
            notifyDataSetChanged()
        }

        //TODO : This is O(N). Reduce its time.
        // 	android.support.v7.util.DiffUtil
        fun removeModel(model : Model) {
            list.remove(model)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder<Model> {
            return BindableViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(layout, parent, false))
        }

        override fun getItemCount() = list.size

        override fun onBindViewHolder(holder: BindableViewHolder<Model>, position: Int) {
            holder.bindModelToView(list[position], binder)
        }
    }

    inner class BindableViewHolder<Model>(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bindModelToView(model: Model, bind : (View, Model) -> Unit) {

            // DEFAULT IMPLEMENTATION
            bind(itemView, model)
        }
    }
}


