package com.example.androidmvvmcleancodeproject.data.remote

import com.example.androidmvvmcleancodeproject.data.model.ContentQuestion
import com.example.androidmvvmcleancodeproject.data.model.UpdateQuestionRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ContentApiService {
    @GET("/api/ContentQuestion/GetContentQuestion")
    suspend fun getContentQuestions(): Response<ContentQuestion>

    @POST("/api/ContentQuestion/UpdateContentQuestionResult")
    suspend fun updateContentQuestionResult(@Body updateQuestionRequest: UpdateQuestionRequest)

}