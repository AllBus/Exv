package com.kos.exv.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kos.exv.db.dao.SpecialityDao
import com.kos.exv.db.dao.WorkerDao
import com.kos.exv.db.dao.WorkerSpecialityDao
import com.kos.exv.models.Speciality
import com.kos.exv.models.Worker
import com.kos.exv.models.WorkerSpeciality

@Database(entities = [Worker::class,Speciality::class,WorkerSpeciality::class], version = 1)
abstract class WorkersDatabase: RoomDatabase() {

    abstract fun workerDao(): WorkerDao
    abstract fun specialityDao(): SpecialityDao
    abstract fun workerSpecialityDao(): WorkerSpecialityDao

}