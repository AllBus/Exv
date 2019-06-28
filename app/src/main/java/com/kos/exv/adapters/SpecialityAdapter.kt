package com.kos.exv.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kos.exv.adapters.viewholders.SpecialityViewHolder
import com.kos.exv.databinding.SpecItemBinding
import com.kos.exv.models.Speciality

class SpecialityAdapter(clickFunction : (Speciality) -> Unit)
    :SimpleAdapter<Speciality,SpecialityViewHolder>(clickFunction){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SpecItemBinding.inflate(inflater, parent, false)
        return SpecialityViewHolder( binding, clickListener)
    }

}