package com.example.videostreamingapp.Model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Video(
    @SerializedName("description")
    val description: String,
    @SerializedName("sources")
    val sources: List<String>,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("thumb")
    val thumb: String,
    @SerializedName("title")
    val title: String
) : Serializable