package com.harbourspace.unsplash.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harbourspace.unsplash.data.model.CityIndex

@Dao
interface CityDAO {

    @Query("SELECT * FROM CityIndex")
    fun getIndexes(): LiveData<List<CityIndex>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(cityIndex: CityIndex)

}