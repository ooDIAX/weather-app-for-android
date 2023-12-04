package com.harbourspace.unsplash.data

import android.app.Application

class AppApplication: Application() {

    val database by lazy { AppDatabase.getDatabase(this) }

    val repository by lazy { CityRepository(database.cityDAO()) }

}