package com.kos.exv.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kos.exv.models.Worker

@Dao
interface WorkerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorker(data: Worker)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkers(data: List<Worker>)

    @Delete
    fun deleteWorker(worker: Worker)

    @Query("DELETE FROM Worker")
    fun clearWorkers()

    @Query("SELECT * FROM Worker WHERE id=:workerId")
    fun getWorkerById(workerId:Int): LiveData<Worker>

    @Query("SELECT Worker.* FROM Worker, WorkerSpeciality WHERE WorkerSpeciality.specialityId = :specialityId AND Worker.id = WorkerSpeciality.workerId ")
    fun getWorkersBySpeciality(specialityId: Int): LiveData<List<Worker>>
}