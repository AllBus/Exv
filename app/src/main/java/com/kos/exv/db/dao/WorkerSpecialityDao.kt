package com.kos.exv.db.dao

import androidx.room.*
import com.kos.exv.models.Speciality
import com.kos.exv.models.WorkerSpeciality
import com.kos.exv.models.ext.WorkersInfoList

@Dao
interface WorkerSpecialityDao : WorkerDao, SpecialityDao  {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: WorkerSpeciality)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: List<WorkerSpeciality>)

    @Query("DELETE FROM WorkerSpeciality")
    fun clear()

    @Query("DELETE FROM WorkerSpeciality WHERE workerId = :worker")
    fun removeForWorker(worker:Int)

    @Query("DELETE FROM WorkerSpeciality WHERE specialityId = :speciality")
    fun removeForSpeciality(speciality: Int)

    @Transaction
    fun clearBase(){
        clear()
        clearWorkers()
        clearSpeciality()
    }

    @Transaction
    fun updateBase(
                   specialities :List<Speciality>,
                   workers: WorkersInfoList){
        clearBase()
        insertSpecialities(specialities)
        insertWorkers(workers.workers)
        insert(workers.workerSpecialities)
    }


}