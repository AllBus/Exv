package com.kos.exv.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kos.exv.models.Speciality
import com.kos.exv.models.Worker

class WorkerListViewModel(application: Application, specialityId: Int) : MainViewModel(application) {
    val workers: LiveData<List<Worker>> = repository.workersForSpeciality(specialityId)
    val speciality: LiveData<Speciality> = repository.speciality(specialityId)
}


class WorkerListViewModelFactory(private val application: Application, private val specialityId: Int) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(WorkerListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            WorkerListViewModel(application, specialityId) as T
        } else {
            throw RuntimeException("Cannot create an instance of $modelClass")
        }
    }

}