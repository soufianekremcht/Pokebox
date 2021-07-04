package com.soufianekre.pokebox.data.network

import com.soufianekre.pokebox.data.models.PokemonItemInfo
import com.soufianekre.pokebox.data.models.PokemonResponse
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Response

class PokeboxClient constructor(
    private val pokeboxService: PokemonApiService
) {

    fun fetchPokemonList(
        page: Int
    ): Flowable<Response<PokemonResponse>> =
        pokeboxService.fetchPokemonResponse(
            limit = PAGING_SIZE,
            offset = page * PAGING_SIZE
        )

    fun fetchPokemonInfo(
        name: String
    ): Single<Response<PokemonItemInfo>> =
        pokeboxService.fetchPokemonInfo(
            name = name
        )

    companion object {
        private const val PAGING_SIZE = 20
    }
}
