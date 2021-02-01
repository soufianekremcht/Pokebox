package com.soufianekre.pokebox.data.db.converter


import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.soufianekre.pokebox.data.models.PokemonItemInfo
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi


@ProvidedTypeConverter
class PokemonSpritesConverter {
    val moshi: Moshi = Moshi.Builder().build()
    @TypeConverter
    fun fromPokemonSprite(pokemonSprite : PokemonItemInfo.PokemonItemSprite) :String{
        //val listType = Types.newParameterizedType(List::class.java, PokemonItemInfo.PokemonItemSprite::class.java)
        val adapter : JsonAdapter<PokemonItemInfo.PokemonItemSprite> = moshi.adapter(PokemonItemInfo.PokemonItemSprite::class.java)
        return adapter.toJson(pokemonSprite)
    }

    @TypeConverter
    fun fromString(value :String): PokemonItemInfo.PokemonItemSprite {
        //val listType = Types.newParameterizedType(List::class.java, PokemonItemInfo.PokemonItemSprite::class.java)
        val moshi : Moshi = Moshi.Builder().build()
        val adapter : JsonAdapter<PokemonItemInfo.PokemonItemSprite> = moshi.adapter(PokemonItemInfo.PokemonItemSprite::class.java)
        return adapter.fromJson(value)!!
    }
}