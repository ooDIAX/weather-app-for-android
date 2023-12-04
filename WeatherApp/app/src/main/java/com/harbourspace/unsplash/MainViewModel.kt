package com.harbourspace.unsplash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.harbourspace.unsplash.data.CityRepository
import com.harbourspace.unsplash.data.model.CityIndex

class MainViewModel(
    private val repository: CityRepository
) : ViewModel() {

    fun getDogsFromDatabase(): LiveData<List<CityIndex>> {
        return repository.allDogs
    }

    fun addDog(city : CityIndex) {
        repository.insert(city)
    }
}