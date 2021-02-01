package com.soufianekre.pokebox.data.db.converter


import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.soufianekre.pokebox.data.models.PokemonItemType
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@ProvidedTypeConverter
class PokemonTypeConverter {


    @TypeConverter
    fun fromPokemonType(pokemonType : List<PokemonItemType>?) :String{
        val listType = Types.newParameterizedType(List::class.java, PokemonItemType::class.java)
        val moshi : Moshi = Moshi.Builder().build()
        val adapter : JsonAdapter<List<PokemonItemType>> = moshi.adapter(listType)
        return adapter.toJson(pokemonType)
    }

    @TypeConverter
    fun fromString(value :String): List<PokemonItemType>? {
        val listType = Types.newParameterizedType(List::class.java, PokemonItemType::class.java)
        val moshi : Moshi = Moshi.Builder().build()
        val adapter : JsonAdapter<List<PokemonItemType>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }
}