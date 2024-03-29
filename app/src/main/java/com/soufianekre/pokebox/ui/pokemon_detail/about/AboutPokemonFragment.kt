package com.soufianekre.pokebox.ui.pokemon_detail.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.soufianekre.pokebox.R
import com.soufianekre.pokebox.data.models.PokemonItemInfo
import com.soufianekre.pokebox.databinding.FragmentPokemonAboutBinding
import com.soufianekre.pokebox.ui.base.BaseFragment

class AboutPokemonFragment(var pokemon : PokemonItemInfo?) : BaseFragment<FragmentPokemonAboutBinding,AboutPokemonViewModel>(){

    lateinit var viewBinding : FragmentPokemonAboutBinding


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
        return R.layout.fragment_pokemon_about
    }

    override fun getViewModel(): AboutPokemonViewModel {
        return ViewModelProvider(this).get(AboutPokemonViewModel::class.java)
    }
}