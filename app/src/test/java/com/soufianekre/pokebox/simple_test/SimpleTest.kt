package com.soufianekre.pokebox.simple_test

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SimpleTest{

    @Before
    fun setup(){

    }


    @Test
    fun test_basic(){
        assertEquals(4+3 , 3)

    }

    @After
    fun onCleared(){

    }
}