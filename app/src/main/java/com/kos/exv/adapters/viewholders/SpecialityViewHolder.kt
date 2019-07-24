package com.kos.exv.adapters.viewholders

import com.kos.exv.databinding.SpecItemBinding
import com.kos.exv.models.Speciality

class SpecialityViewHolder(private val binding: SpecItemBinding, clickFunction : (Speciality) -> Unit) :
    SimpleBindingViewHolder<Speciality>(binding, clickFunction) {

    override fun bind(data: Speciality) {
        super.bind(data)

        binding.speciality = data
        binding.executePendingBindings()
    }
}