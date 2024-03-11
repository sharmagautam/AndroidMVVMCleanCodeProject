package com.example.androidmvvmcleancodeproject.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.androidmvvmcleancodeproject.R
import com.example.androidmvvmcleancodeproject.databinding.ActivityMainBinding
import com.example.androidmvvmcleancodeproject.ui.viewmodel.ContentQuestionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
    }
}