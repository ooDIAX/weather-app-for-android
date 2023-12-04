package com.harbourspace.unsplash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.harbourspace.unsplash.api.VisualCrossingApiProvider
import com.harbourspace.unsplash.data.cb.VisualCrossingResult
import com.harbourspace.unsplash.data.model.VisualCrossingItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "VisualCrossingViewModel"

class VisualCrossingViewModel : ViewModel(), VisualCrossingResult {

    private val _visualCrossingItem = MutableLiveData<VisualCrossingItem>()
    val visualCrossingItem: LiveData<VisualCrossingItem> = _visualCrossingItem

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val provider by lazy {
        VisualCrossingApiProvider()
    }

    // Example function to fetch Visual Crossing data
    fun fetchVisualCrossingData(location: String, date1: String, date2: String) {
        _loading.value = true
        provider.fetchVisualCrossingData(location, date1, date2, this)
    }

    override fun onDataFetchedSuccess(data: VisualCrossingItem) {
        Log.d(TAG, "onDataFetchedSuccess | Received data")
        _visualCrossingItem.value = data
    }

    override fun onDataFetchedFailed() {
        Log.e(TAG, "onDataFetchedFailed | Unable to retrieve Visual Crossing data")
    }
}