package com.soufianekre.pokebox.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.soufianekre.pokebox.data.models.PokemonItemInfo
import io.reactivex.Completable
import io.reactivex.Single


@Dao
public interface PokemonInfoDao{
    @Query("SELECT * FROM pokemon_info WHERE name= :name")
    fun getPokemonInfo(name: String): Single<PokemonItemInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemonInfo(pokemonInfo : PokemonItemInfo) : Completable

}