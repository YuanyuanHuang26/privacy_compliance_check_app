
package com.yuanyuansapplication.app.appcomponents.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val BASE_URL = "http://10.5.86.40:5001/"

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)  // Connection timeout
        .readTimeout(60, TimeUnit.SECONDS)     // Read timeout
        .writeTimeout(60, TimeUnit.SECONDS)    // Write timeout
        .build()

    val instance: RetrofitService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)  // Set the custom OkHttp client
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)
    }
}

