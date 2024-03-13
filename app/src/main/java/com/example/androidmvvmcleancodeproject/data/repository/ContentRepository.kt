package com.example.androidmvvmcleancodeproject.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidmvvmcleancodeproject.data.model.ContentQuestion
import com.example.androidmvvmcleancodeproject.data.model.UpdateQuestionRequest
import com.example.androidmvvmcleancodeproject.data.remote.ContentApiService
import com.example.androidmvvmcleancodeproject.ui.utils.NetworkResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class ContentRepository @Inject constructor(private val apiService: ContentApiService) {
    private val _contentResponseLiveData = MutableLiveData<NetworkResult<ContentQuestion>>()
    val contentResponseLiveData: LiveData<NetworkResult<ContentQuestion>>
        get() = _contentResponseLiveData

    suspend fun getContentQuestions() {
        _contentResponseLiveData.postValue(NetworkResult.Loading())
        val response = apiService.getContentQuestions()
        handleResponse(response)
    }

    /**
    * need to handle response
     * no data is coming after calling the api successfully
    * */
    suspend fun updateContentQuestion(updateQuestionRequest: UpdateQuestionRequest){
        apiService.updateContentQuestionResult(updateQuestionRequest)
        /*TODO Handle response */
    }

    private fun handleResponse(response: Response<ContentQuestion>) {
        if (response.isSuccessful && response.body() != null) {
            _contentResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
        }
        else if(response.errorBody()!=null){
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _contentResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        }
        else{
            _contentResponseLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }
}