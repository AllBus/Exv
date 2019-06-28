package com.kos.exv.models.dbjson

data class SpecialityDBModel(
    val specialty_id:Int= 0,
    val name:String = ""
) {

    override fun equals(other: Any?): Boolean {
        return when (other){
            is SpecialityDBModel -> specialty_id == other.specialty_id
            else -> false
        }
    }

    override fun hashCode(): Int {
        return specialty_id
    }

}
