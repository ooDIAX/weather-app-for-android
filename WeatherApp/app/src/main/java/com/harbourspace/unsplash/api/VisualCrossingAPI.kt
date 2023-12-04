package com.harbourspace.unsplash.api

import com.harbourspace.unsplash.data.model.VisualCrossingItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

private const val AUTHORIZATION_CLIENT_ID = "Client-ID"
private const val ACCESS_KEY = "S6TLVPDEESQBZTHWBTNXEX9F7"


interface VisualCrossingAPI {

    @GET("VisualCrossingWebServices/rest/services/timeline/{location}/{date1}/{date2}")
    fun getForecast(
        @Path("location") location: String,
        @Path("date1") date1: String,
        @Path("date2") date2: String,
        @Query("key") apiKey: String
    ): Call<VisualCrossingItem>

}