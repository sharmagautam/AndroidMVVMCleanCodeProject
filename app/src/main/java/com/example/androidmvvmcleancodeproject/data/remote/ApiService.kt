package com.example.androidmvvmcleancodeproject.data.remote

import com.example.androidmvvmcleancodeproject.data.model.ContentQuestion
import com.example.androidmvvmcleancodeproject.data.model.LoginRequest
import com.example.androidmvvmcleancodeproject.data.model.LoginResponse
import com.example.androidmvvmcleancodeproject.ui.utils.NetworkResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("/api/ContentQuestion/GetContentQuestion")
    suspend fun getContentQuestions(): ContentQuestion

    @POST("/api/User/authenticate")
    suspend fun login(@Body loginRequest: LoginRequest): NetworkResult<LoginResponse>
}