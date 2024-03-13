package com.example.androidmvvmcleancodeproject.data.remote

import com.example.androidmvvmcleancodeproject.data.model.LoginRequest
import com.example.androidmvvmcleancodeproject.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiService {
    @POST("/api/User/authenticate")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}