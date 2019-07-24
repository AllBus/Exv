package com.kos.exv.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kos.exv.adapters.viewholders.WorkerViewHolder
import com.kos.exv.databinding.WorkerItemBinding
import com.kos.exv.models.Worker

class WorkerAdapter( clickFunction : (Worker) -> Unit)
    :SimpleAdapter<Worker,WorkerViewHolder>(clickFunction){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = WorkerItemBinding.inflate(inflater, parent, false)
        return WorkerViewHolder( binding, clickFunction)
    }

}