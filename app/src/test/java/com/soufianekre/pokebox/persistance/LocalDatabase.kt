package com.soufianekre.pokebox.persistance

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.soufianekre.pokebox.data.db.AppDatabase
import com.soufianekre.pokebox.data.db.converter.PokemonSpritesConverter
import com.soufianekre.pokebox.data.db.converter.PokemonStatConverter
import com.soufianekre.pokebox.data.db.converter.PokemonTypeConverter
import com.squareup.moshi.Moshi
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
abstract class LocalDatabase {
    lateinit var db: AppDatabase

    @Before
    fun initDB() {
        db = Room.inMemoryDatabaseBuilder(getApplicationContext(), AppDatabase::class.java)
            .allowMainThreadQueries()
            .addTypeConverter(PokemonTypeConverter())
            .addTypeConverter(PokemonSpritesConverter())
            .addTypeConverter(PokemonStatConverter())
            .build()
    }

    @After
    fun closeDB() {
        db.close()
    }
}
