package com.example.androidmvvmcleancodeproject.data.model


import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("page")
    val page: List<Page>,
    @SerializedName("survey")
    val survey: Survey
)