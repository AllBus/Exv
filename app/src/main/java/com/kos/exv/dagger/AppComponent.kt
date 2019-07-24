package com.kos.exv.dagger

import com.kos.exv.viewmodels.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, DatabaseModule::class, NetworkModule::class])
@Singleton
interface AppComponent {

    fun inject(mainViewModel: MainViewModel)

}