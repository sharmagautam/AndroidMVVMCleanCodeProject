package com.example.androidmvvmcleancodeproject.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.androidmvvmcleancodeproject.R
import com.example.androidmvvmcleancodeproject.databinding.FragmentSurveyQuestionBinding
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
            model.calculateMarks()
        }
        model.contentQuestion.observe(viewLifecycleOwner) {
            for (contentQuestions in it.content) {
                val textView = TextView(requireContext())
                textView.text = contentQuestions.survey.surveyName
                binding.container.addView(textView)

                var selectedRadioButton: RadioButton? =
                    null  // Variable to keep track of the selected RadioButton

                for (question in contentQuestions.survey.surveyQuestions) {
                    val questionTextView = TextView(requireContext())
                    questionTextView.text = question.questions
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
                            binding.container.addView(checkBox)
                        }
                    } else {
                        question.answers.forEach { options ->
                            var radioButton = RadioButton(requireContext())
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
                            binding.container.addView(radioButton)
                        }
                    }
                }
            }
        }
    }
}