package com.kos.exv.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.kos.exv.R
import com.kos.exv.databinding.WorkerDetailFragmentBinding
import com.kos.exv.viewmodels.WorkerDetailViewModel
import com.kos.exv.viewmodels.WorkerDetailViewModelFactory

class WorkerDetailFragment : MainFragment() {

    private lateinit var viewModel: WorkerDetailViewModel
    private lateinit var binding: WorkerDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = WorkerDetailFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val workerId = getWorkerId()

        viewModel = ViewModelProviders.of(this, WorkerDetailViewModelFactory(application(), workerId))
            .get(WorkerDetailViewModel::class.java)

        binding.vm = viewModel

        title(R.string.title_worker_detail_fragment)
        setupToolbar()
    }

    private fun getWorkerId(): Int {
        return arguments?.let { arguments ->
            val args = WorkerDetailFragmentArgs.fromBundle(arguments)
            args.workerId
        } ?: 0
    }
}
