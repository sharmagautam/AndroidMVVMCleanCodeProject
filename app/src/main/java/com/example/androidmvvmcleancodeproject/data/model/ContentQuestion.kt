package com.example.androidmvvmcleancodeproject.data.model


import com.google.gson.annotations.SerializedName

data class ContentQuestion(
    @SerializedName("content")
    val content: List<Content>
)