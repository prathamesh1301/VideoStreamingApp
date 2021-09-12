package com.example.videostreamingapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {
    val obj=Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/bikashthapa01/myvideos-android-app/master/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(APIService::class.java)
}