package com.soufianekre.pokebox.ui.pokemon_detail.base_stats

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.soufianekre.pokebox.R
import com.soufianekre.pokebox.data.models.PokemonItemInfo
import com.soufianekre.pokebox.databinding.FragmentPokemonBaseStatesBinding
import com.soufianekre.pokebox.ui.base.BaseFragment

class PokemonBaseStatsFragment(var pokemonInfo : PokemonItemInfo?) : BaseFragment<FragmentPokemonBaseStatesBinding, PokemonBaseStatsViewModel>() {

    lateinit var viewBinding: FragmentPokemonBaseStatesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = getViewBinding()
        viewBinding.apply {
            pokemonData = pokemonInfo
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_pokemon_base_states
    }

    override fun getViewModel(): PokemonBaseStatsViewModel {
       return ViewModelProvider(this).get(PokemonBaseStatsViewModel::class.java)
    }
}