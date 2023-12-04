package com.harbourspace.unsplash.api

import androidx.compose.ui.res.stringResource
import com.harbourspace.unsplash.R
import com.harbourspace.unsplash.data.cb.VisualCrossingResult
import com.harbourspace.unsplash.data.model.VisualCrossingItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://weather.visualcrossing.com/"
private const val ACCESS_KEY = "S6TLVPDEESQBZTHWBTNXEX9F7"

class VisualCrossingApiProvider {

    private val retrofit by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val visualCrossingService: VisualCrossingAPI by lazy {
        retrofit.create(VisualCrossingAPI::class.java)
    }


    fun fetchVisualCrossingData(
        location: String,
        date1: String,
        date2: String,
        cb: VisualCrossingResult
    ) {
        val call = retrofit.create(VisualCrossingAPI::class.java)
            .getForecast(location, date1, date2, ACCESS_KEY)

        call.enqueue(object : Callback<VisualCrossingItem> {
            override fun onResponse(
                call: Call<VisualCrossingItem>,
                response: Response<VisualCrossingItem>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    cb.onDataFetchedSuccess(response.body()!!)
                } else {
                    cb.onDataFetchedFailed()
                }
            }

            override fun onFailure(call: Call<VisualCrossingItem>, t: Throwable) {
                cb.onDataFetchedFailed()
            }
        })
    }

}