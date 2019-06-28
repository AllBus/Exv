package com.kos.exv.net

import com.google.gson.GsonBuilder
import com.kos.exv.common.net.DataAsyncLoader
import com.kos.exv.db.dao.WorkerSpecialityDao
import com.kos.exv.models.dbjson.StructDbModel


object Api {

    const val HOST = "https://gitlab.65apps.com"

    const val DB_API = "/65gb/static/raw/master/testTask.json"


    private fun load(
        uri: String,
        constructor: (String) -> Int,
        onComplete: (Int) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val loader = DataAsyncLoader(
            uri,
            constructor,
            onComplete,
            onFailure
        )
        loader.execute()
    }

    fun loadData(
        uri: String,
        workerSpecialityDao: WorkerSpecialityDao,
        onComplete: (Int) -> Unit,
        onFailure: (Exception) -> Unit
    ) {

        load(
            uri,
            { json ->
                val gson = GsonBuilder().create()
                val items = gson.fromJson(json, StructDbModel::class.java)
                workerSpecialityDao.updateBase(
                    items.specialities(),
                    items.workers())
                1
            },
            onComplete,
            onFailure
        )

    }


}