package com.soufianekre.pokebox.test_common

import com.soufianekre.pokebox.data.models.PokemonItem
import com.soufianekre.pokebox.data.models.PokemonItemInfo

object MyMockUtil {

    fun mockPokemon() = PokemonItem(
        name = "bulbasaur",
        url = "https://pokeapi.co/api/v2/pokemon/1/"
    )

    fun mockPokemonList() = listOf(mockPokemon())

    fun mockPokemonInfo() = PokemonItemInfo(
        pokemonId = 1,
        name = "bulbasaur",
        height = 7,
        weight = 69,
        experience = 60,
        types = emptyList()
    )
}