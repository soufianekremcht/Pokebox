package com.soufianekre.pokebox.network

import junit.framework.Assert.assertEquals
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response


@RunWith(JUnit4::class)
class ApiResponseTest {

    @Test
    fun exception() {

    }

    @Test
    fun success() {

        val apiResponse =
            Response.success("foo")
        if (apiResponse.isSuccessful) {
            assertEquals(apiResponse.body(), "foo")
        }


    }
}