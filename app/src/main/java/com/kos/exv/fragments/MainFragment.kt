package com.kos.exv.fragments

import android.app.Application
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.kos.exv.R

open class MainFragment : Fragment() {

    open fun title(text: String) {
        view?.findViewById<Toolbar>(R.id.toolbar)?.title = text
    }

    open fun title(@StringRes resId: Int) {
        title(getString(resId))
    }

    fun application(): Application {
        return activity?.application
            ?: throw IllegalStateException("Your activity/fragment is not yet attached to Application.")
    }

    fun navigateUp(){
        view?.let {
            Navigation.findNavController(it).navigateUp()
        }
    }

    fun setupToolbar(){
        view?.findViewById<Toolbar>(R.id.toolbar)?.let{toolbar ->
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
            toolbar.setNavigationOnClickListener {
                navigateUp()
            }
        }
    }

    inline fun <reified T : ViewModel> injectViewModel(factory: ViewModelProvider.Factory): T {
        return ViewModelProviders.of(this, factory)[T::class.java]
    }
}

