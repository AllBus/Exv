package com.kos.exv.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Worker(
    @PrimaryKey
    val id:Int,
    val firstName:String,
    val lastName:String,
    val avatarUrl:String,
    @Embedded
    val birthday:Age
)