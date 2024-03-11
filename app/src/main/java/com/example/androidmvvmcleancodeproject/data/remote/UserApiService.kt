package com.example.androidmvvmcleancodeproject.data.remote

import com.example.androidmvvmcleancodeproject.data.model.LoginRequest
import com.example.androidmvvmcleancodeproject.data.model.LoginResponse
import com.example.androidmvvmcleancodeproject.ui.utils.NetworkResult
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiService {
   /* var logIntercepter: LoginRequest
        get() = LoginRequest("byftest010","test1234")
        set(value) = value*/

    @POST("/api/User/authenticate")
    suspend fun login(@Body loginRequest: LoginRequest): NetworkResult<LoginResponse>
}