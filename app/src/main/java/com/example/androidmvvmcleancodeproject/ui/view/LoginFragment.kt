package com.example.androidmvvmcleancodeproject.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.androidmvvmcleancodeproject.R
import com.example.androidmvvmcleancodeproject.databinding.FragmentLoginBinding
import com.example.androidmvvmcleancodeproject.ui.utils.Helpers.toast
import com.example.androidmvvmcleancodeproject.ui.utils.NetworkResult
import com.example.androidmvvmcleancodeproject.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var model: LoginViewModel
    private var binding: FragmentLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindObserver()
        binding?.buttonLogIn?.setOnClickListener {
            val email = binding?.editTextEmail?.text.toString().trim()
            val password = binding?.editTextPassword?.text.toString().trim()

            if (model.isValidEmail(email) && model.isValidPassword(password)) {
                login(email, password)
            } else {
                requireActivity().toast("Invalid email or password")
            }
        }
    }

    private fun bindObserver() {
        model.loginData.observe(viewLifecycleOwner) {
            binding?.progressBar?.isVisible = false
            when (it) {
                is NetworkResult.Success -> {
                    findNavController().navigate(
                        R.id.action_loginFragment_to_surveyQuestionFragment,
                        null
                    )
                }

                is NetworkResult.Error -> {
                    requireActivity().toast("" + it.message)
                }

                is NetworkResult.Loading -> {
                    binding?.progressBar?.isVisible = true
                }
            }
        }
    }

    private fun login(email: String, password: String) {
        model.login(email, password)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}