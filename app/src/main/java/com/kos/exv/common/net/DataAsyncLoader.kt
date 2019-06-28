package com.kos.exv.common.net

import android.os.AsyncTask
import okhttp3.OkHttpClient
import okhttp3.Request

class DataAsyncLoader<T>(
    private val uri: String,
    private val save: (String) -> T,
    private val onComplete: (T) -> Unit,
    private val onFailure: (Exception) -> Unit
) : AsyncTask<Unit, Unit, Unit>() {


    override fun doInBackground(vararg params: Unit?) {
        try {
            val client = OkHttpClient()

            val request = Request.Builder()
                .url(uri)
                .build()

            val result = (client.newCall(request).execute().use { response ->
                response.body()?.string() ?: ""
            })

            val r = save(result)

            onComplete(r)

        } catch (e: Exception) {
            onFailure(e)
        }
    }
}