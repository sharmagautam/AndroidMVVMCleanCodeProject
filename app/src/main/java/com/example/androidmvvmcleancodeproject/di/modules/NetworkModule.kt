package com.example.androidmvvmcleancodeproject.di.modules

import android.util.Log
import com.example.androidmvvmcleancodeproject.data.remote.UserApiService
import com.example.androidmvvmcleancodeproject.data.remote.AuthIntercepter
import com.example.androidmvvmcleancodeproject.data.remote.ContentApiService
import com.example.androidmvvmcleancodeproject.ui.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: AuthIntercepter): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    fun provideUserApiService(retrofitBuilder: Retrofit.Builder): UserApiService {
        Log.e("NetworkModule","user api service=> ${retrofitBuilder.build()}")
        return retrofitBuilder.build().create(UserApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideContentApiService(retrofitBuilder: Retrofit.Builder, okHttpClient: OkHttpClient): ContentApiService {
        Log.e("NetworkModule","content api service=> ${retrofitBuilder.build()}")
        return retrofitBuilder.client(okHttpClient).build().create(ContentApiService::class.java)
    }

}