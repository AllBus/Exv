package com.kos.exv.adapters

import androidx.recyclerview.widget.RecyclerView
import com.kos.exv.adapters.viewholders.SimpleViewHolder

abstract class SimpleAdapter<T:Any, VH:SimpleViewHolder<T>>(
    val clickFunction : (T) -> Unit
    ) : RecyclerView.Adapter<VH>() {

    private var list : List<T> = listOf()

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(list[position])
    }

    fun changeList(newList: List<T>){
        if (list !== newList) {
            list = newList
            notifyDataSetChanged()
        }
    }
}