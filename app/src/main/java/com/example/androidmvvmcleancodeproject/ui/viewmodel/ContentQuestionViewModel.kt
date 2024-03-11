package com.example.androidmvvmcleancodeproject.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidmvvmcleancodeproject.data.model.Answer
import com.example.androidmvvmcleancodeproject.data.model.ContentQuestion
import com.example.androidmvvmcleancodeproject.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentQuestionViewModel @Inject constructor(private val contentRepository: Repository): ViewModel() {
    private val _contentQuestion = MutableLiveData<ContentQuestion>()
    val contentQuestion: LiveData<ContentQuestion> get() = _contentQuestion
    fun calculateMarks(){
        var totalMarks = 0
        var contetQuestions = _contentQuestion.value?.content?.get(0)?.survey?.surveyQuestions
        if (contetQuestions != null) {
            for (quest in contetQuestions) {
                if(quest.isMultiChoice){
                    if(checkMultiChoiceCorrectAns(quest.answers)) {
                        totalMarks += 10
                    }
                }
                else{
                   if(checkSingleChoiceCorrectAns(quest.answers)){
                        totalMarks += 10
                   }
                }
            }
        }
        Log.e("contenetquestionViewModel",
            "total marks is => $totalMarks ")
    }
    /**
    * return true when user selected option, equals mark equals to true
    *  mark == optionSelected == 1
    * */
    private fun checkSingleChoiceCorrectAns(ansList: List<Answer>) : Boolean{
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
    private fun checkMultiChoiceCorrectAns(ansList: List<Answer>) : Boolean{
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

    fun getRemoteContentQuestions(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val content = contentRepository.getRemoteContentQuestions()
                _contentQuestion.postValue(content)
                Log.e("UserListViewModel", "content => ${content}")
            }
            catch (e: Exception){
                Log.e("UserListViewModel", "Exception => ${e.message}")
            }
        }
    }
}