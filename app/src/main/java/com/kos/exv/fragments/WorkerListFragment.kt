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
import com.kos.exv.R
import com.kos.exv.adapters.WorkerAdapter
import com.kos.exv.viewmodels.WorkerListViewModel
import com.kos.exv.viewmodels.WorkerListViewModelFactory
import kotlinx.android.synthetic.main.spec_fragment.view.*

class WorkerListFragment : MainFragment() {

    private lateinit var viewModel: WorkerListViewModel
    private var adapter: WorkerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.worker_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = view.list
        adapter = WorkerAdapter {
            val directions = WorkerListFragmentDirections.actionWorkerListFragmentToWorkerDetailFragment(it.id)
            Navigation.findNavController(view).navigate(
                R.id.action_workerListFragment_to_workerDetailFragment,
                directions.arguments
            )
        }

        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val specId = getSpecialityId()

        viewModel = ViewModelProviders.
            of(this, WorkerListViewModelFactory(application(), specId)).
            get(WorkerListViewModel::class.java)

        viewModel.workers.observe(this.viewLifecycleOwner, Observer { items ->
            items?.let { adapter?.changeList(it) }
        })

        viewModel.speciality.observe(this.viewLifecycleOwner, Observer { speciality ->
            speciality?.let { title(it.name) }
        })

        setupToolbar()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
    }

    private fun getSpecialityId(): Int {
        return arguments?.let { arguments ->
            val args = WorkerListFragmentArgs.fromBundle(arguments)
            args.specId
        } ?: 0
    }

}
