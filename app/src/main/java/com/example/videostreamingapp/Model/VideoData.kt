package com.example.videostreamingapp.Model


import com.google.gson.annotations.SerializedName

data class VideoData(
    @SerializedName("categories")
    val categories: List<Category>
)