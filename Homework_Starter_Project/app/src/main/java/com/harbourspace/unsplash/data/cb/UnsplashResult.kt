package com.harbourspace.unsplash.data.cb

import com.harbourspace.unsplash.data.model.UnsplashCollection
import com.harbourspace.unsplash.data.model.UnsplashItem

interface UnsplashResult {

  fun onDataFetchedSuccess(images: List<UnsplashItem>)


  fun onDatajsonFetchedSuccess(images: UnsplashItem)

  fun onDataFetchedFailed()

  fun onCollectionsFetchedSuccess(collections: List<UnsplashCollection>)
}