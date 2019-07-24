package com.kos.exv.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.kos.exv.App
import com.kos.exv.repositories.WorkerRepository
import javax.inject.Inject

open class MainViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    protected lateinit var repository: WorkerRepository
    val loadFailure: LiveData<Throwable>

    init {
        App.component.inject(this)
        loadFailure = repository.loadFailure
    }

    fun reloadDataIfNeed(forceReload: Boolean) {
        repository.reloadDataIfNeed(forceReload)
    }


}