package com.soufianekre.pokebox.ui.pokemon_detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.soufianekre.pokebox.data.models.PokemonItemInfo
import com.soufianekre.pokebox.ui.pokemon_detail.about.AboutPokemonFragment
import com.soufianekre.pokebox.ui.pokemon_detail.base_stats.PokemonBaseStatsFragment
import com.soufianekre.pokebox.ui.pokemon_detail.evolutions.PokemonEvolutionFragment
import com.soufianekre.pokebox.ui.pokemon_detail.moves.PokemonMovesFragment

class PokemonDetailViewPagerAdapter(var fm: FragmentManager,val pokemon : PokemonItemInfo?) : FragmentStatePagerAdapter(
    fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    override fun getCount(): Int {
        return 4;
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> return AboutPokemonFragment(pokemon)
            1 -> return PokemonBaseStatsFragment(pokemon)
            2 -> return PokemonEvolutionFragment(pokemon)
            3 -> return PokemonMovesFragment(pokemon)
        }

        return AboutPokemonFragment(pokemon)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> return "About"
            1 -> return "Base Stats"
            2 -> return "Evolution"
            3 -> return "Moves"
        }
        return "About"
    }


}