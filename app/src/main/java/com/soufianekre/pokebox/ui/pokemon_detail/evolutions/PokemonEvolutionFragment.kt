package com.soufianekre.pokebox.ui.pokemon_detail.evolutions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.soufianekre.pokebox.R
import com.soufianekre.pokebox.data.models.PokemonItemInfo
import com.soufianekre.pokebox.databinding.FragmentPokemonEvolutionBinding
import com.soufianekre.pokebox.ui.base.BaseFragment

public class PokemonEvolutionFragment(var pokemon : PokemonItemInfo?) : BaseFragment<FragmentPokemonEvolutionBinding,PokemonEvolutionsViewModel>(){



    lateinit var viewBinding : FragmentPokemonEvolutionBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = getViewBinding()

    }
    override fun getLayoutId(): Int {
        return R.layout.fragment_pokemon_evolution
    }

    override fun getViewModel(): PokemonEvolutionsViewModel {
        return ViewModelProvider(this).get(PokemonEvolutionsViewModel::class.java)
    }

}