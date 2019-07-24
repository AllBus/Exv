package com.kos.exv.dagger

import android.content.Context
import androidx.room.Room
import com.kos.exv.db.WorkersDatabase
import com.kos.exv.db.dao.WorkerSpecialityDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            WorkersDatabase::class.java,
            "workers_db")
            .build()

    @Singleton
    @Provides
    fun provideWorkerSpecialityDao(database: WorkersDatabase): WorkerSpecialityDao {
        return database.workerSpecialityDao()
    }
}