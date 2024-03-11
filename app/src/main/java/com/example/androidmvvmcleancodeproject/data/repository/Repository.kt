package com.example.androidmvvmcleancodeproject.data.repository

import com.example.androidmvvmcleancodeproject.data.model.ContentQuestion
import com.example.androidmvvmcleancodeproject.data.model.LoginRequest
import com.example.androidmvvmcleancodeproject.data.model.LoginResponse
import com.example.androidmvvmcleancodeproject.ui.utils.NetworkResult

interface Repository {
    suspend fun getRemoteContentQuestions(): ContentQuestion
    suspend fun login(loginRequest: LoginRequest): NetworkResult<LoginResponse>
}