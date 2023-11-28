package com.harbourspace.unsplash.api

import com.harbourspace.unsplash.data.model.UnsplashCollection
import com.harbourspace.unsplash.data.model.UnsplashItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

private const val AUTHORIZATION_CLIENT_ID = "Client-ID"
private const val ACCESS_KEY = "uEhbeQHvfaUdp7CPUQyVY1Dklxby1GjC1KMEui6FTgs"

interface UnsplashApi {

  @Headers("Authorization: $AUTHORIZATION_CLIENT_ID $ACCESS_KEY")
  @GET("photos")
  fun fetchPhotos() : Call<List<UnsplashItem>>


  @Headers("Authorization: $AUTHORIZATION_CLIENT_ID $ACCESS_KEY")
  @GET("photos/{id}")
  fun fetchjson(@Path(value = "id") id : String) : Call<UnsplashItem>

  @Headers("Authorization: $AUTHORIZATION_CLIENT_ID $ACCESS_KEY")
  @GET("collections")
  fun fetchCollections() : Call<List<UnsplashCollection>>


}