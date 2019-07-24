package com.kos.exv.adapters.viewholders

import androidx.databinding.ViewDataBinding

open class SimpleBindingViewHolder<T : Any>(binding: ViewDataBinding, clickFunction : (T) -> Unit) :
    SimpleViewHolder<T>(binding.root, clickFunction)