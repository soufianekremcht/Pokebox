@file:Suppress("UNCHECKED_CAST")

package com.soufianekre.pokebox

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.soufianekre.pokebox.ui.main.MainViewModel
import com.soufianekre.pokebox.ui.pokemon_detail.PokemonDetailViewModel
import com.soufianekre.pokebox.ui.main.pokemon_list.PokemonListViewModel

class MyViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel() as T
            modelClass.isAssignableFrom(PokemonListViewModel::class.java) -> PokemonListViewModel() as T
            modelClass.isAssignableFrom(PokemonDetailViewModel::class.java) -> return PokemonDetailViewModel() as T
            else -> throw IllegalArgumentException("Unknown ViewModel class " + modelClass.name)
        }
    }






}