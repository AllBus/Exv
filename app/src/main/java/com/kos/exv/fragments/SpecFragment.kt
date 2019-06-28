package com.kos.exv.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.kos.exv.R
import com.kos.exv.adapters.SpecialityAdapter
import com.kos.exv.viewmodels.SpecViewModel
import kotlinx.android.synthetic.main.spec_fragment.view.*

class SpecFragment : MainFragment() {

    private lateinit var viewModel: SpecViewModel
    private lateinit var list: RecyclerView
    private lateinit var adapter: SpecialityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.spec_fragment, container, false)

        list = view.list
        adapter = SpecialityAdapter {
            val directions = SpecFragmentDirections.actionSpecFragmentToWorkerListFragment(it.id)
            Navigation.findNavController(view).navigate(
                R.id.action_specFragment_to_workerListFragment,
                directions.arguments
            )
        }

        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SpecViewModel::class.java)


        viewModel.loadFailure.observe(this, Observer { e ->
            e?.let {

                if (!viewModel.hasData()) {
                    val snackbar = Snackbar.make(list, R.string.error_load_data, Snackbar.LENGTH_INDEFINITE)
                    snackbar.setAction(R.string.error_load_data_retry_button) {
                        snackbar.dismiss()
                        viewModel.reloadDataIfNeed(true)
                    }
                    snackbar.show()
                }
            }
        })

        viewModel.specialities.observe(this, Observer { items ->
            items?.let {
                adapter.changeList(it)
            }
        })

        viewModel.reloadDataIfNeed(false)

        title(R.string.title_specialities)
    }

}
