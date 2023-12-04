package com.harbourspace.unsplash.data.cb

import com.harbourspace.unsplash.data.model.VisualCrossingItem

interface VisualCrossingResult {
    fun onDataFetchedSuccess(data: VisualCrossingItem)

    fun onDataFetchedFailed()
}