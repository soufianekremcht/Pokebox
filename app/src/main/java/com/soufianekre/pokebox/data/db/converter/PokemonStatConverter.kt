package com.soufianekre.pokebox.data.db.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.soufianekre.pokebox.data.models.PokemonItemStat
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@ProvidedTypeConverter
public class PokemonStatConverter {

    @TypeConverter
    fun fromPokemonStat(pokemonStat : List<PokemonItemStat>?) :String{
        val listType = Types.newParameterizedType(List::class.java, PokemonItemStat::class.java)
        val moshi : Moshi = Moshi.Builder().build()
        val adapter : JsonAdapter<List<PokemonItemStat>> = moshi.adapter(listType)
        return adapter.toJson(pokemonStat)
    }

    @TypeConverter
    fun fromString(value :String): List<PokemonItemStat>? {
        val listType = Types.newParameterizedType(List::class.java, PokemonItemStat::class.java)
        val moshi : Moshi = Moshi.Builder().build()
        val adapter : JsonAdapter<List<PokemonItemStat>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }


}