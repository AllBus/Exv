package com.kos.exv.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kos.exv.adapters.viewholders.SimpleViewHolder

abstract class SimpleAdapter<T:Any, VH:SimpleViewHolder<T>>(
    val clickFunction : (T) -> Unit
    ) : RecyclerView.Adapter<VH>() {

    private var list : List<T> = listOf()

    protected val clickListener = View.OnClickListener{ view ->
        when (val holder = view.tag){
            is SimpleViewHolder<*> -> {
                @Suppress("UNCHECKED_CAST")
                clickFunction( holder.data as T)
            }
            else -> {}
        }
    }

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