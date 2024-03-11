package com.example.androidmvvmcleancodeproject.data.model


import com.google.gson.annotations.SerializedName

data class Answer(
    @SerializedName("answerId")
    val answerId: String,
    @SerializedName("mark")
    val mark: Int,
    @SerializedName("optionText")
    val optionText: String,

    var optionSelected : Int ? = 0,


)