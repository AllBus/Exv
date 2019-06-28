package com.kos.exv.repositories

import androidx.lifecycle.MutableLiveData
import com.kos.exv.db.dao.WorkerSpecialityDao
import com.kos.exv.net.Api

class WorkerRepository(private val workerSpecialityDao: WorkerSpecialityDao) {

    val loadFailure: MutableLiveData<Exception> = MutableLiveData()
    val specialities  = workerSpecialityDao.getAllSpeciality()

    fun speciality(specialityId:Int ) = workerSpecialityDao.getSpecialityById(specialityId)
    fun specialitiesForWorker(workerId: Int) = workerSpecialityDao.getSpecialitiesByWorker(workerId)

    fun worker(workerId: Int) = workerSpecialityDao.getWorkerById(workerId)
    fun workersForSpeciality(specialityId:Int ) = workerSpecialityDao.getWorkersBySpeciality(specialityId)

    private var lastLoadTime = 0L

    fun reloadDataIfNeed(forceReload: Boolean) {
        val currentTime = System.currentTimeMillis()
        loadFailure.postValue(null)
        if (forceReload || specialities.value == null || lastLoadTime + RELOAD_TIME < currentTime) {
            lastLoadTime = currentTime

            Api.loadData(Api.HOST + Api.DB_API, workerSpecialityDao,
                onComplete = {
                    loadFailure.postValue(null)
                },
                onFailure = { e ->
                    loadFailure.postValue(e)
                })
        }
    }



    companion object {
        const val RELOAD_TIME = 50 * 60 * 1000 //50 minutes
    }

}