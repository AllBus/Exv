package com.kos.exv.net

import com.kos.exv.models.dbjson.StructDbModel
import io.reactivex.Flowable
import retrofit2.http.GET

interface Api {

    @GET("/65gb/static/raw/master/testTask.json")
    fun loadData() : Flowable<StructDbModel>

    companion object {

        const val HOST = "https://gitlab.65apps.com"

    }
}