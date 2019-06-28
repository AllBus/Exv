package com.kos.exv.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import com.kos.exv.models.Speciality

class SpecViewModel(application: Application) : MainViewModel(application)  {


    val specialities : LiveData<List<Speciality>> = repository.specialities

    fun hasData(): Boolean {
        return !specialities.value.isNullOrEmpty()
    }
}
