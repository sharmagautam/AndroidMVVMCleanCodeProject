package com.example.androidmvvmcleancodeproject.data.remote

import android.util.Log
import com.example.androidmvvmcleancodeproject.ui.utils.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthIntercepter @Inject constructor(): Interceptor {
    @Inject
    lateinit var tokenManager: TokenManager

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val token = tokenManager.getToken()
        request.addHeader("Authorization", "Bearer $token")
        Log.e("AuthIntercepter", "auth=> ${request.build()}")
        return chain.proceed(request.build())
    }
}
