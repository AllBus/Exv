package com.kos.exv.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.kos.exv.db.WorkersDatabase
import com.kos.exv.repositories.WorkerRepository

open class MainViewModel(application: Application) : AndroidViewModel(application) {

    protected val repository: WorkerRepository
    val loadFailure: LiveData<Exception>

    init {
        val dao = WorkersDatabase.getDatabase(application).workerSpecialityDao()
        repository = WorkerRepository(dao)

        loadFailure = repository.loadFailure
    }

    fun reloadDataIfNeed(forceReload: Boolean) {
        repository.reloadDataIfNeed(forceReload)
    }


}