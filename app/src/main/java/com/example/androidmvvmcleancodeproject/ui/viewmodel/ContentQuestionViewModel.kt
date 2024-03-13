package com.example.androidmvvmcleancodeproject.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidmvvmcleancodeproject.data.model.Answer
import com.example.androidmvvmcleancodeproject.data.model.ContentQuestion
import com.example.androidmvvmcleancodeproject.data.model.UpdateQuestionRequest
import com.example.androidmvvmcleancodeproject.data.repository.ContentRepository
import com.example.androidmvvmcleancodeproject.ui.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentQuestionViewModel @Inject constructor(private val contentRepository: ContentRepository) :
    ViewModel() {
    private val _updateRequestLiveData =  MutableLiveData<UpdateQuestionRequest>()
    val updateRequestLiveData : LiveData<UpdateQuestionRequest> get() = _updateRequestLiveData

    val contentResponseLiveData: LiveData<NetworkResult<ContentQuestion>>
        get() = contentRepository.contentResponseLiveData

    fun calculateMarksAndupdateQuestionContent() {
        val content = contentResponseLiveData.value?.data?.content?.get(0)?.survey
        val contetQuestions = content?.surveyQuestions

        var totalMarks = 0
        val surveyId = content?.surveyId

        if (contetQuestions != null) {
            for (quest in contetQuestions) {
                if (quest.isMultiChoice) {
                    if (checkMultiChoiceCorrectAns(quest.answers)) {
                        totalMarks += 10
                    }
                } else {
                    if (checkSingleChoiceCorrectAns(quest.answers)) {
                        totalMarks += 10
                    }
                }
            }
        }
        Log.e(
            "contenetquestionViewModel",
            "total marks is => $totalMarks "
        )
        val updateContentQuestion = surveyId?.let { UpdateQuestionRequest(it, totalMarks) }
        updateContentQuestion?.let { updateQuestionContent(it) }
    }

    /**
     * return true when user selected option, equals mark equals to true
     *  mark == optionSelected == 1
     * */
    private fun checkSingleChoiceCorrectAns(ansList: List<Answer>): Boolean {
        val count = ansList.count {
            it.mark == 1 && it.optionSelected == 1
        }
        return count == 1
    }

    /**
     * this return true when user selected all correct answer
     * count: return true if mark and  optionSelected is true
     * selectedAnsCount: user selected count
     * correctAnsCount: correct ans count
     * */
    private fun checkMultiChoiceCorrectAns(ansList: List<Answer>): Boolean {
        val count = ansList.count {
            it.mark == 1 && it.optionSelected == 1
        }
        val selectedAnsCount = ansList.count {
            it.optionSelected == 1
        }
        val correctAnsCount = ansList.count {
            it.mark == 1
        }
        return count == selectedAnsCount && selectedAnsCount == correctAnsCount
    }

    fun getRemoteContentQuestions() {
        viewModelScope.launch(Dispatchers.IO) {
            contentRepository.getContentQuestions()
        }
    }

    fun updateQuestionContent(updateContentQuestion: UpdateQuestionRequest) {
        _updateRequestLiveData.value = updateContentQuestion
        viewModelScope.launch(Dispatchers.IO) {
            contentRepository.updateContentQuestion(updateContentQuestion)
        }
    }
}