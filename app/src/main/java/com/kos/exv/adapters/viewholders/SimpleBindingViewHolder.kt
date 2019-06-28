package com.kos.exv.adapters.viewholders

import android.view.View
import androidx.databinding.ViewDataBinding

open class SimpleBindingViewHolder<T : Any>(binding: ViewDataBinding, clickListener: View.OnClickListener) :
    SimpleViewHolder<T>(binding.root, clickListener)