package com.kos.exv.adapters.viewholders

import android.view.View
import com.kos.exv.databinding.WorkerItemBinding
import com.kos.exv.models.Worker

class WorkerViewHolder(private val binding: WorkerItemBinding, clickListener: View.OnClickListener) :
    SimpleBindingViewHolder<Worker>(binding, clickListener) {

    override fun bind(data: Worker) {
        super.bind(data)

        binding.worker = data
        binding.executePendingBindings()
    }
}