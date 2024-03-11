package com.example.androidmvvmcleancodeproject.di.modules

import android.util.Log
import com.example.androidmvvmcleancodeproject.data.remote.ApiService
import com.example.androidmvvmcleancodeproject.data.remote.AuthIntercepter
import com.example.androidmvvmcleancodeproject.data.remote.UserApiService
import com.example.androidmvvmcleancodeproject.data.repository.Repository
import com.example.androidmvvmcleancodeproject.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkhttpClient(intercepter: AuthIntercepter): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(intercepter)
            .build()
    }

    @Provides
    @Singleton
    @Named("WithAuth")
    fun provideRetrofitWithAuth(okHttpClient: OkHttpClient): Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl("https://interview.bigyellowfish.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
    }
    @Provides
    @Singleton
    @Named("WithOutAuth")
    fun provideRetrofitWithoutAuth(): Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl("https://interview.bigyellowfish.io/")
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    @Singleton
    fun provideContentRepository(contentApiService: ApiService,
                                loginApiService: UserApiService)
    :Repository =  RepositoryImpl(contentApiService,loginApiService)

    @Provides
    @Singleton
    fun provideLoginService(@Named("WithAuth") retrofitBuilder: Retrofit.Builder): UserApiService {
        Log.e("NetworkModule", "provideLoginService>>>>>>")
        return retrofitBuilder.build().create(UserApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideContentQuestionService(@Named("WithAuth")retrofitBuilder: Retrofit.Builder): ApiService{
        Log.e("NetworkModule", "provideContentQuestionService>>>>>>")
        return retrofitBuilder.build().create(ApiService::class.java)
    }

}