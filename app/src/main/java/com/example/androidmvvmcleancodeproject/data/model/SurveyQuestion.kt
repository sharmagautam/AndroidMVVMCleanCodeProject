package com.example.androidmvvmcleancodeproject.data.model


import com.google.gson.annotations.SerializedName

data class SurveyQuestion(
    @SerializedName("answers")
    val answers: List<Answer>,
    @SerializedName("description")
    var description: String,
    @SerializedName("isMultiChoice")
    var isMultiChoice: Boolean,
    @SerializedName("question_id")
    var questionId: String,
    @SerializedName("questions")
    var questions: String
)