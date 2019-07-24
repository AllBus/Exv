package com.kos.exv.dagger

import com.kos.exv.net.NetworkService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideNetwork() = NetworkService()
}