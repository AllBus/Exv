package com.kos.exv.models.dbjson

import com.kos.exv.models.Age
import com.kos.exv.models.Speciality
import com.kos.exv.models.Worker
import com.kos.exv.models.WorkerSpeciality
import com.kos.exv.models.ext.WorkersInfoList

data class StructDbModel(
    val response: List<WorkerDBModel> = listOf()
) {

    fun specialities(): List<Speciality> {
        return response.
            flatMap { it.specialty?: listOf() }.
            toSet().
            map { spec ->
                Speciality(
                    id = spec.specialty_id,
                    name = spec.name
                )
            }
    }

    fun workers(): WorkersInfoList {

        val (workers, specs) = response.mapIndexed { index, workerModel ->
            val workerId = index + 1

            val worker = Worker(
                id = workerId,
                firstName = workerModel.f_name?: "",
                lastName = workerModel.l_name?: "",
                avatarUrl = workerModel.avatr_url ?: "",
                birthday = Age.parse(workerModel.birthday ?: "")
            )

            val specialities = workerModel.specialty?.map {
                WorkerSpeciality(
                    workerId = worker.id,
                    specialityId = it.specialty_id
                )
            }?: listOf()

            Pair(worker, specialities)
        }.unzip()

        return WorkersInfoList(workers, specs.flatten())
    }

}