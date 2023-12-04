package com.harbourspace.unsplash.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CityIndex")
class CityIndex(
    @PrimaryKey(autoGenerate = false)
    var id: Int,

    @ColumnInfo(name = "index")
    val index : Int,
)