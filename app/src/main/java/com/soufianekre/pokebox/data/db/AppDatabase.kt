package com.soufianekre.pokebox.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.soufianekre.pokebox.data.db.converter.PokemonSpritesConverter
import com.soufianekre.pokebox.data.db.converter.PokemonStatConverter
import com.soufianekre.pokebox.data.db.converter.PokemonTypeConverter
import com.soufianekre.pokebox.data.db.dao.PokemonDao
import com.soufianekre.pokebox.data.db.dao.PokemonInfoDao
import com.soufianekre.pokebox.data.models.PokemonItem
import com.soufianekre.pokebox.data.models.PokemonItemInfo


@Database(entities = [PokemonItem::class,PokemonItemInfo::class],version = 2,exportSchema = false)
@TypeConverters(value = [PokemonStatConverter::class,PokemonTypeConverter::class,
    PokemonSpritesConverter::class])

abstract class AppDatabase : RoomDatabase() {

    companion object{
        var instance : AppDatabase? = null
        fun getInstance(context : Context): AppDatabase {
            if (instance == null){
                return Room.databaseBuilder(context,AppDatabase::class.java,"pokebox.db")
                    .addTypeConverter(PokemonStatConverter())
                    .addTypeConverter(PokemonTypeConverter())
                    .addTypeConverter(PokemonSpritesConverter())
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }



    abstract fun pokemonInfoDao() : PokemonInfoDao
    abstract fun pokemonDao() : PokemonDao


}