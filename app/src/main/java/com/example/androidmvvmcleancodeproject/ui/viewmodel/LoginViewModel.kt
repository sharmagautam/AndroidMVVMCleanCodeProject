package com.example.androidmvvmcleancodeproject.ui.viewmodel

import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidmvvmcleancodeproject.data.model.LoginRequest
import com.example.androidmvvmcleancodeproject.data.model.LoginResponse
import com.example.androidmvvmcleancodeproject.data.repository.Repository
import com.example.androidmvvmcleancodeproject.ui.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor (private val repository: Repository) : ViewModel() {
    private var _loginData = MutableLiveData<NetworkResult<LoginResponse>>()
    val loginData: LiveData<NetworkResult<LoginResponse>> get() = _loginData

    fun login(email: String, password: String){
        val loginRequest = LoginRequest(email, password)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repository.login(loginRequest)
                 _loginData.postValue(result)
                Log.e("LoginViewModel", "login response => $result")
            }
            catch (e: Exception){
                Log.e("LoginViewModel", "Exception => ${e.message}")
            }
        }
    }
     fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

     fun isValidPassword(password: String): Boolean {
        return !TextUtils.isEmpty(password) && password.length >= 6
    }
}