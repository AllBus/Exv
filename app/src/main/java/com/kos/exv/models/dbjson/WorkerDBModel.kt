package com.kos.exv.models.dbjson

data class WorkerDBModel(
    val f_name: String? = "",
    val l_name: String? = "",
    val birthday: String? = "",
    val avatr_url: String? = "",
    val specialty: List<SpecialityDBModel>? = listOf()
)