package com.kos.exv.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class SimpleViewHolder<T : Any>(itemView: View, clickListener: View.OnClickListener) :
    RecyclerView.ViewHolder(itemView) {

    lateinit var data: T

    init {
        itemView.setOnClickListener(clickListener)
        @Suppress("LeakingThis")
        itemView.tag = this
    }

    open fun bind(data: T) {
        this.data = data
    }
}