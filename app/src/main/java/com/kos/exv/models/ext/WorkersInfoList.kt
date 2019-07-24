package com.kos.exv.models.ext

import com.kos.exv.models.Worker
import com.kos.exv.models.WorkerSpeciality
import io.reactivex.Flowable


class WorkersInfoList(
    val workers : Flowable<Worker>,
    val workerSpecialities :Flowable<WorkerSpeciality>
)