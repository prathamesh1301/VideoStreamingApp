package com.example.videostreamingapp.network

import com.example.videostreamingapp.Model.Category
import com.example.videostreamingapp.Model.VideoData
import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("data.json")
    fun getVideos():Call<VideoData>
}