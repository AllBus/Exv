package com.kos.exv.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WorkerSpeciality(
    val workerId:Int,
    val specialityId:Int,
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0
)