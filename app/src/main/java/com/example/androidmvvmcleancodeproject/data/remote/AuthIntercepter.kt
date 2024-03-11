package com.example.androidmvvmcleancodeproject.data.remote

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthIntercepter @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsImN0eSI6IkpXVCJ9.eyJpZCI6IjEwIiwibmJmIjoxNzEwMTI0NzY4LCJleHAiOjE3MTAxNTM1NjgsImlhdCI6MTcxMDEyNDc2OH0.Qx9ZoaBECl7Kqi9vDJI-hanxkYY9CMLQxWbTtG4fEP8"
        request.addHeader("Authorization", "Bearer $token")
        Log.e("AuthIntercepter", "request normal=> ${request.build()}")
        return chain.proceed(request.build())
    }
}

class LogIntercepter @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        Log.e("AuthIntercepter", "request login => ${request.build()}")
        return chain.proceed(request.build())
    }
}