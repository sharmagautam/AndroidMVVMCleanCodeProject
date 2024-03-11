package com.example.androidmvvmcleancodeproject.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("id")
    var id : Int?    = null,
    @SerializedName("firstName")
    var firstName : String? = null,
    @SerializedName("lastName")
    var lastName  : String? = null,
    @SerializedName("username")
    var username  : String? = null,
    @SerializedName("token")
    var token : String? = null
)