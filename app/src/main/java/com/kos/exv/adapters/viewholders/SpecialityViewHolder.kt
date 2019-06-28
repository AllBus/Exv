package com.kos.exv.adapters.viewholders

import android.view.View
import com.kos.exv.databinding.SpecItemBinding
import com.kos.exv.models.Speciality

class SpecialityViewHolder(private val binding: SpecItemBinding, clickListener: View.OnClickListener) :
    SimpleBindingViewHolder<Speciality>(binding, clickListener) {

    override fun bind(data: Speciality) {
        super.bind(data)

        binding.speciality = data
        binding.executePendingBindings()
    }
}