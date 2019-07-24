package com.kos.exv.adapters.viewholders

import com.kos.exv.databinding.WorkerItemBinding
import com.kos.exv.models.Worker

class WorkerViewHolder(private val binding: WorkerItemBinding, clickFunction : (Worker) -> Unit) :
    SimpleBindingViewHolder<Worker>(binding, clickFunction) {

    override fun bind(data: Worker) {
        super.bind(data)

        binding.worker = data
        binding.executePendingBindings()
    }
}