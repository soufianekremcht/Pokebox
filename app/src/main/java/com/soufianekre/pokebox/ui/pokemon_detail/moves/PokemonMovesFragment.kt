package com.soufianekre.pokebox.ui.pokemon_detail.moves

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.soufianekre.pokebox.R
import com.soufianekre.pokebox.data.models.PokemonItemInfo
import com.soufianekre.pokebox.databinding.FragmentPokemonMovesBinding
import com.soufianekre.pokebox.ui.base.BaseFragment

class PokemonMovesFragment(var pokemon :PokemonItemInfo?) :
    BaseFragment<FragmentPokemonMovesBinding, PokemonMovesViewModel>() {

    lateinit var viewBinding : FragmentPokemonMovesBinding


    override fun getLayoutId(): Int {
        return R.layout.fragment_pokemon_moves
    }

    override fun getViewModel(): PokemonMovesViewModel {
        return ViewModelProvider(this).get(PokemonMovesViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = getViewBinding()
    }
}