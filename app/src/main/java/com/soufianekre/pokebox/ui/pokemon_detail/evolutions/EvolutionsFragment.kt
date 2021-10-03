package com.soufianekre.pokebox.ui.pokemon_detail.evolutions

import com.soufianekre.pokebox.R
import com.soufianekre.pokebox.databinding.FragmentsEvolutionsBinding
import com.soufianekre.pokebox.ui.base.BaseFragment

public class EvolutionsFragment : BaseFragment<FragmentsEvolutionsBinding,EvolutionsViewModel>(){



    override fun getLayoutId(): Int {
        return R.layout.fragment_poke_details_evolution
    }

    override fun getViewModel(): EvolutionsViewModel {
        TODO("Not yet implemented")
    }

}