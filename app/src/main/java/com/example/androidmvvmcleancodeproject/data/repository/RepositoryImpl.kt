package com.example.androidmvvmcleancodeproject.data.repository

import com.example.androidmvvmcleancodeproject.data.model.ContentQuestion
import com.example.androidmvvmcleancodeproject.data.remote.ApiService
import com.example.androidmvvmcleancodeproject.data.model.LoginRequest
import com.example.androidmvvmcleancodeproject.data.model.LoginResponse
import com.example.androidmvvmcleancodeproject.data.remote.UserApiService
import com.example.androidmvvmcleancodeproject.ui.utils.NetworkResult
import javax.inject.Inject
import javax.inject.Named

class  RepositoryImpl @Inject constructor(private val contentApiService: ApiService,
                                          private val loginApiService: UserApiService
) :Repository {
    override suspend fun getRemoteContentQuestions(): ContentQuestion {
       return contentApiService.getContentQuestions()
    }

    override suspend fun login(loginRequest: LoginRequest): NetworkResult<LoginResponse> {
        return loginApiService.login(loginRequest)
    }

}