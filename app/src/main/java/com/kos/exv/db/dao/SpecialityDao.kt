package com.kos.exv.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kos.exv.models.Speciality

@Dao
interface SpecialityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpeciality(data: Speciality)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpecialities(data: List<Speciality>)

    @Delete
    fun deleteSpeciality(speciality: Speciality)

    @Query("DELETE FROM Speciality")
    fun clearSpeciality()

    @Query("SELECT * FROM Speciality")
    fun getAllSpeciality(): LiveData<List<Speciality>>

    @Query("SELECT Speciality.* FROM Speciality, WorkerSpeciality WHERE WorkerSpeciality.workerId = :workerId AND Speciality.id = WorkerSpeciality.specialityId ")
    fun getSpecialitiesByWorker(workerId: Int): LiveData<List<Speciality>>

    @Query("SELECT * FROM Speciality WHERE id = :id")
    fun getSpecialityById(id:Int): LiveData<Speciality>
}