package com.kos.exv.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Speciality(
    @PrimaryKey
    val id:Int,
    val name:String
)