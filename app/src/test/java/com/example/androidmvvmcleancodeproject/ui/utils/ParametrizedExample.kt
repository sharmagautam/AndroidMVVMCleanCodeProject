package com.example.androidmvvmcleancodeproject.ui.utils

import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(value = Parameterized::class)
class ParametrizedExample(private val input: String, private val expectedValue: Boolean) {

    @Test
    fun test(){
        val helper = Helper()
        val result = helper.isPalindrome(input)
        assertEquals(expectedValue, result)
    }

    companion object{
        @JvmStatic
        @Parameters(name = "index: {0} is pallindrome {1}")
        fun data(): List<Array<Any>>{
            return listOf(
                arrayOf("hello",false),
                arrayOf("level",true),
                arrayOf("", true),
                arrayOf("a", true)
            )
        }
    }
}