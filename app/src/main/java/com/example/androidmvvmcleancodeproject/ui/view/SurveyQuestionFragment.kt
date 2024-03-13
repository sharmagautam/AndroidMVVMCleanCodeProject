package com.example.androidmvvmcleancodeproject.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.androidmvvmcleancodeproject.R
import com.example.androidmvvmcleancodeproject.data.model.Content
import com.example.androidmvvmcleancodeproject.databinding.FragmentSurveyQuestionBinding
import com.example.androidmvvmcleancodeproject.ui.utils.Helpers.toast
import com.example.androidmvvmcleancodeproject.ui.utils.NetworkResult
import com.example.androidmvvmcleancodeproject.ui.viewmodel.ContentQuestionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SurveyQuestionFragment : Fragment() {
    private lateinit var model: ContentQuestionViewModel
    private lateinit var binding: FragmentSurveyQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this)[ContentQuestionViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_survey_question, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.getRemoteContentQuestions()

        binding.btnSubmit.setOnClickListener {
            model.calculateMarksAndupdateQuestionContent()
        }
        bindObserver()
    }
    private fun bindObserver() {
        model.contentResponseLiveData.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = false
            when (it) {
                is NetworkResult.Success -> {
                    it.data?.content?.let { it1 -> createUI(it1) }
                }

                is NetworkResult.Error -> {
                    requireActivity().toast("" + it.message)
                }

                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true
                }
            }
        }

        model.updateRequestLiveData.observe(viewLifecycleOwner){
            requireActivity().toast("survey ID: ${it.surveyId} Total Marks: ${it.mark}")
        }
    }

    fun createUI(content: List<Content>) {
        for (contentQuestions in content) {
            val textView = TextView(requireContext())
            textView.text = contentQuestions.survey.surveyName
            textView.setPadding(16, 16, 16, 16)
            binding.container.addView(textView)

            var selectedRadioButton: RadioButton? = null  // Variable to keep track of the selected RadioButton

            for (question in contentQuestions.survey.surveyQuestions) {
                val questionTextView = TextView(requireContext())
                questionTextView.text = question.questions
                questionTextView.setPadding(16, 16, 16, 16)
                binding.container.addView(questionTextView)

                if (question.isMultiChoice) {
                    question.answers.forEach { options ->
                        val checkBox = CheckBox(requireContext())
                        checkBox.text = options.optionText
                        checkBox.id = options.answerId.toInt()
                        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                            if (isChecked) {
                                options.optionSelected = 1
                            } else {
                                options.optionSelected = 0
                            }
                        }
                        checkBox.setPadding(16, 16, 16, 16)
                        binding.container.addView(checkBox)
                    }
                } else {
                    question.answers.forEach { options ->
                        val radioButton = RadioButton(requireContext())
                        radioButton.text = options.optionText
                        radioButton.id = options.answerId.toInt()
                        radioButton.setOnCheckedChangeListener { buttonView, isChecked ->
                            if (isChecked) {
                                options.optionSelected = 1
                                selectedRadioButton?.isChecked = false
                                selectedRadioButton = buttonView as RadioButton
                            } else {
                                options.optionSelected = 0
                            }
                        }
                        radioButton.setPadding(16, 16, 16, 16)
                        binding.container.addView(radioButton)
                    }
                }
            }
        }

    }
}