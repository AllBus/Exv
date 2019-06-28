package com.kos.exv.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kos.exv.models.Worker
import com.kos.exv.models.ext.SpecialitiesList

class WorkerDetailViewModel(application: Application, workerId: Int) : MainViewModel(application) {
    val worker: LiveData<Worker> = repository.worker(workerId)
    val specialities: LiveData<SpecialitiesList> =
        Transformations.map( repository.specialitiesForWorker(workerId)) { list ->
            list?.let{ SpecialitiesList(it)}}

}


class WorkerDetailViewModelFactory(private val application: Application, private val workerId: Int) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(WorkerDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            WorkerDetailViewModel(application, workerId) as T
        } else {
            throw RuntimeException("Cannot create an instance of $modelClass")
        }
    }

}
