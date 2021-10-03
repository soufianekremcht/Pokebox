package com.soufianekre.pokebox.ui.pokemon_detail.moves

import androidx.lifecycle.ViewModelProvider
import com.soufianekre.pokebox.R
import com.soufianekre.pokebox.databinding.FragmentPokeDetailsMovesBinding
import com.soufianekre.pokebox.ui.base.BaseFragment

class PokemonMovesFragment :
    BaseFragment<FragmentPokeDetailsMovesBinding, PokemonMovesViewModel>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_pokemon_moves
    }

    override fun getViewModel(): PokemonMovesViewModel {
        return ViewModelProvider(this).get(PokemonMovesViewModel::class.java)
    }
}