package com.kos.exv.net

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService {

    private val retrofit = Retrofit.Builder()
        .baseUrl(Api.HOST)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();

    fun getApi() : Api = retrofit.create(Api::class.java)
}