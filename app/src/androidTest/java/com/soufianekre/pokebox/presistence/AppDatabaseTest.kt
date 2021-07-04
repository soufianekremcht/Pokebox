package com.soufianekre.pokebox.presistence

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.soufianekre.pokebox.data.db.AppDatabase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

    lateinit var db : AppDatabase

    val context = InstrumentationRegistry.getInstrumentation().targetContext


    @Before
    fun setup(){

        db = Room.inMemoryDatabaseBuilder(context!!,AppDatabase::class.java).build()
    }

    @Test
    fun test_insert(){

    }

    @After
    fun onCleared(){

    }
}