package com.kos.exv.models.dbjson

import com.kos.exv.models.Age
import com.kos.exv.models.Speciality
import com.kos.exv.models.Worker
import com.kos.exv.models.WorkerSpeciality
import com.kos.exv.models.ext.WorkersInfoList
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction

data class StructDbModel(
    val response: List<WorkerDBModel> = listOf()
) {

    fun specialities(): Flowable<Speciality> {

        return Flowable
            .fromIterable(response)
            .flatMapIterable { it.specialty ?: listOf() }
            .distinct()
            .map { spec ->
                Speciality(
                    id = spec.specialty_id,
                    name = spec.name
                )
            }
    }

    fun workers(): WorkersInfoList {

        val range = Flowable.range(1, response.size)
        val indexedWorkers = Flowable
            .fromIterable(response)
            .zipWith(range, BiFunction { workerModel:WorkerDBModel, workerId :Int -> Pair(workerModel, workerId) })

        val workers = indexedWorkers.map { (workerModel, workerId) ->
            Worker(
                id = workerId,
                firstName = workerModel.f_name ?: "",
                lastName = workerModel.l_name ?: "",
                avatarUrl = workerModel.avatr_url ?: "",
                birthday = Age.parse(workerModel.birthday ?: "")
            )
        }

        val specialities = indexedWorkers.flatMapIterable { (workerModel, workerId) ->
            workerModel.specialty?.map { dbModel ->
                WorkerSpeciality(
                    workerId = workerId,
                    specialityId = dbModel.specialty_id
                )
            } ?: listOf<WorkerSpeciality>()
        }

        return WorkersInfoList(workers, specialities)
    }

}