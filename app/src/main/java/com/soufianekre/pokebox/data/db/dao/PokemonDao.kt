package com.soufianekre.pokebox.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.soufianekre.pokebox.data.models.PokemonItem
import io.reactivex.Completable
import io.reactivex.Flowable


@Dao
public interface PokemonDao{
    @Query("SELECT * FROM pokemon")
    fun getPokemonList() : Flowable<List<PokemonItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemons(pokemonList :List<PokemonItem>) : Completable
}