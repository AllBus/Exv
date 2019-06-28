package com.kos.exv.models.ext

import com.kos.exv.models.Worker
import com.kos.exv.models.WorkerSpeciality

class WorkersInfoList(
    val workers :List<Worker>,
    val workerSpecialities :List<WorkerSpeciality>
)