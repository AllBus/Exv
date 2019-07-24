package com.kos.exv.repositories

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.kos.exv.db.dao.WorkerSpecialityDao
import com.kos.exv.models.Speciality
import com.kos.exv.models.ext.WorkersInfoList
import com.kos.exv.net.NetworkService
import io.reactivex.Flowable
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkerRepository
@Inject constructor(
    _workerSpecialityDao: WorkerSpecialityDao,
    _networkService: NetworkService
) {

    private val workerSpecialityDao = _workerSpecialityDao
    private val networkService = _networkService

    val loadFailure: MutableLiveData<Throwable> = MutableLiveData()
    val specialities = workerSpecialityDao.getAllSpeciality()

    fun speciality(specialityId: Int) = workerSpecialityDao.getSpecialityById(specialityId)
    fun specialitiesForWorker(workerId: Int) = workerSpecialityDao.getSpecialitiesByWorker(workerId)

    fun worker(workerId: Int) = workerSpecialityDao.getWorkerById(workerId)
    fun workersForSpeciality(specialityId: Int) = workerSpecialityDao.getWorkersBySpeciality(specialityId)

    private var lastLoadTime = 0L


    private fun updateBase(specialities: Flowable<Speciality>, workers: WorkersInfoList): Flowable<Unit> {

        val clearBase = Single.fromCallable { workerSpecialityDao.clearBase() }
        val specialitiesList = specialities.toList().map { workerSpecialityDao.insertSpecialities(it) }
        val workersList = workers.workers.toList().map { workerSpecialityDao.insertWorkers(it) }
        val workerSpecialitiesList = workers.workerSpecialities.toList().map { workerSpecialityDao.insert(it) }

        return Single.concat(
            clearBase,
            specialitiesList,
            workersList,
            workerSpecialitiesList
        )
    }

    @SuppressLint("CheckResult")
    fun reloadDataIfNeed(forceReload: Boolean) {
        val currentTime = System.currentTimeMillis()
        loadFailure.postValue(null)
        if (forceReload || specialities.value == null || lastLoadTime + RELOAD_TIME < currentTime) {
            lastLoadTime = currentTime

            networkService.getApi()
                .loadData()
                .flatMap { dbModel ->
                    updateBase(
                        dbModel.specialities(),
                        dbModel.workers()
                    )
                }
                .subscribeOn(Schedulers.io())
                .doOnNext { Log.d(TAG, "Next") }
                .doOnError { Log.d(TAG, "Error") }
                .doOnComplete { Log.d(TAG, "Complete") }
                .doOnSubscribe { Log.d(TAG, "Subscribe") }
                .subscribe(
                    { },
                    { e -> loadFailure.postValue(e) },
                    { loadFailure.postValue(null) },
                    { t -> loadFailure.postValue(null); t.request(Long.MAX_VALUE); }
                )


        }
    }


    companion object {
        const val RELOAD_TIME = 50 * 60 * 1000 //50 minutes

        const val TAG = "WorkerRepository"
    }

}
