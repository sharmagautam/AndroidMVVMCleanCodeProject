package com.example.androidmvvmcleancodeproject.data.model


import com.google.gson.annotations.SerializedName

data class Survey(
    @SerializedName("surveyId")
    val surveyId: Int,
    @SerializedName("surveyName")
    val surveyName: String,
    @SerializedName("surveyQuestions")
    var surveyQuestions: List<SurveyQuestion>
)