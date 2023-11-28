package com.harbourspace.unsplash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.harbourspace.unsplash.api.UnsplashApiProvider
import com.harbourspace.unsplash.data.cb.UnsplashResult
import com.harbourspace.unsplash.data.model.UnsplashCollection
import com.harbourspace.unsplash.data.model.UnsplashItem

private const val TAG = "UnsplashViewModel"
class UnsplashViewModel : ViewModel(), UnsplashResult {

  private val _items = MutableLiveData<List<UnsplashItem>>()
  val items: LiveData<List<UnsplashItem>> = _items

  private val _item = MutableLiveData<UnsplashItem>()
  val item: LiveData<UnsplashItem> = _item

  private val _collections = MutableLiveData<List<UnsplashCollection>>()
  val collections: LiveData<List<UnsplashCollection>> = _collections

  private val _loading = MutableLiveData(false)
  val loading: LiveData<Boolean> = _loading

  private val provider by lazy {
    UnsplashApiProvider()
  }

  fun fetchjson(id : String){
    provider.fetchjson(id, this)
  }

  fun fetchImages() {
    provider.fetchImages(this)
  }


  fun fetchCollections() {
    provider.fetchCollections(this)
  }

  override fun onCollectionsFetchedSuccess(collections: List<UnsplashCollection>) {
    Log.d(TAG, "onCollectionsFetchedSuccess | Received ${collections.size} collections")
    _collections.value = collections
  }

  override fun onDataFetchedSuccess(images: List<UnsplashItem>) {
    Log.d(TAG, "onDataFetchedSuccess | Received ${images.size} images")
    _items.value = images
  }

  override fun onDatajsonFetchedSuccess(item: UnsplashItem) {
    Log.d(TAG, "onDataFetchedjsonSuccess | Received json")
    _item.value = item
  }

  override fun onDataFetchedFailed() {
    Log.e(TAG, "onDataFetchedFailed | Unable to retrieve images")
  }
}