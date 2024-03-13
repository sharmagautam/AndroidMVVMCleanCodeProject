package com.example.androidmvvmcleancodeproject.ui.viewmodel

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidmvvmcleancodeproject.data.model.LoginRequest
import com.example.androidmvvmcleancodeproject.data.model.LoginResponse
import com.example.androidmvvmcleancodeproject.data.repository.UserRepository
import com.example.androidmvvmcleancodeproject.ui.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    val userResponseLiveData: LiveData<NetworkResult<LoginResponse>>
        get() = repository.loginResponseLiveData

    fun login(email: String, password: String) {
        val loginRequest = LoginRequest(email, password)
        viewModelScope.launch(Dispatchers.IO) {
            repository.login(loginRequest)
        }
    }

    fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email)
    }

    fun isValidPassword(password: String): Boolean {
        return !TextUtils.isEmpty(password) && password.length >= 6
    }
}