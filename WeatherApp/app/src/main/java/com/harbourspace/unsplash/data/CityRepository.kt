package com.harbourspace.unsplash.data

import androidx.lifecycle.LiveData
import com.harbourspace.unsplash.data.model.CityIndex

class CityRepository(private val cityDAO: CityDAO) {

    val allDogs: LiveData<List<CityIndex>> = cityDAO.getIndexes()

    fun insert(dog: CityIndex) {
        AppDatabase.databaseWriteExecutor.execute {
            cityDAO.insert(dog)
        }
    }
}