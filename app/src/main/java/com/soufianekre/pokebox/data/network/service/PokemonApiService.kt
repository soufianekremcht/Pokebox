package com.soufianekre.pokebox.data.network.service

import com.soufianekre.pokebox.data.models.PokemonItemInfo
import com.soufianekre.pokebox.data.models.PokemonResults
import com.soufianekre.pokebox.data.network.APIConstants.POKEMON
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PokemonApiService {

    @GET(POKEMON)
    fun getPokemonResponse(@Query("limit") limit :Int = 20, @Query("offset") offset :Int = 0) : Flowable<Response<PokemonResults>>

    @GET("$POKEMON{name}")
    fun getPokemon(@Path("name") name :String) : Single<Response<PokemonItemInfo>>

}