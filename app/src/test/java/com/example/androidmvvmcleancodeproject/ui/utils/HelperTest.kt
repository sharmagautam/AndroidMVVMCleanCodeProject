package com.example.androidmvvmcleancodeproject.ui.utils

import org.junit.After
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class HelperTest {
    private lateinit var helper:Helper
    @Before
    fun setUp(){
        println("set up called")
        helper = Helper()
    }

    @After
    fun tearDown(){
        println("tear down called")
    }

    @Test
    fun isPalindrome() {
        val result = helper.isPalindrome("hello")
        assertEquals(false, result)
    }

    @Test
    fun isPalindrome_inputString_level_expectedTrue(){
        var result = helper.isPalindrome("level")
        assertEquals(true, result)
    }
}