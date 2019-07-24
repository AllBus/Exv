package com.kos.exv.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class SimpleViewHolder<T : Any>(itemView: View, private val clickFunction : (T) -> Unit) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {

    var data: T? = null

    override fun onClick(v: View?) {
        data?.let(clickFunction)
    }

    init {
        itemView.setOnClickListener(this)
    }

    open fun bind(data: T) {
        this.data = data
    }
}