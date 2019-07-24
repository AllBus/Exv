package com.kos.exv

import android.app.Application
import android.content.Context
import com.kos.exv.dagger.AppComponent
import com.kos.exv.dagger.AppModule
import com.kos.exv.dagger.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        buildComponents(this)
    }

    companion object{
        lateinit var component : AppComponent

         fun buildComponents(context: Context) {
             component=DaggerAppComponent.builder().appModule(AppModule(context)).build()
         }
    }
}