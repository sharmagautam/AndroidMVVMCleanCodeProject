package com.example.androidmvvmcleancodeproject.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidmvvmcleancodeproject.data.remote.UserApiService
import com.example.androidmvvmcleancodeproject.data.model.LoginRequest
import com.example.androidmvvmcleancodeproject.data.model.LoginResponse
import com.example.androidmvvmcleancodeproject.ui.utils.NetworkResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class  UserRepository @Inject constructor(private val userApiService: UserApiService
)  {
    private val _loginResponseLiveData = MutableLiveData<NetworkResult<LoginResponse>>()
    val loginResponseLiveData: LiveData<NetworkResult<LoginResponse>>
        get() = _loginResponseLiveData

    suspend fun login(loginRequest: LoginRequest) {
        _loginResponseLiveData.postValue(NetworkResult.Loading())
        val response = userApiService.login(loginRequest)
        handleResponse(response)
    }

    private fun handleResponse(response: Response<LoginResponse>) {
        if (response.isSuccessful && response.body() != null) {
            _loginResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
        }
        else if(response.errorBody()!=null){
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _loginResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        }
        else{
            _loginResponseLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }
}