package com.soufianekre.pokebox.ui.dashboard

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.soufianekre.pokebox.R
import com.soufianekre.pokebox.data.models.dashboard.PokeboxOption
import com.soufianekre.pokebox.data.models.dashboard.PokemonNews
import com.soufianekre.pokebox.databinding.ActivityDashboardBinding
import com.soufianekre.pokebox.ui.base.BaseActivity
import com.soufianekre.pokebox.ui.main.MainActivity

class DashboardActivity : BaseActivity<ActivityDashboardBinding, DashboardViewModel>() {


    lateinit var viewBinding: ActivityDashboardBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = getViewDataBinding()
        viewBinding.apply {
            viewModel = getCurrentViewModel()
        }
        setupUI()
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_dashboard
    }

    override fun getCurrentViewModel(): DashboardViewModel {
        return ViewModelProvider(this).get(DashboardViewModel::class.java)
    }

    private fun setupUI() {

        val options =
            arrayListOf("Pokedex", "Locations", "Context", "Moves", "Items", "Type Effects")
        val appOptionList: ArrayList<PokeboxOption> = ArrayList()
        for (i in 0..options.size - 1) {
            appOptionList.add(PokeboxOption(options[i], Color.BLUE))
        }

        val optionsAdapter = PokeboxOptionsAdapter(this, appOptionList);
        viewBinding.pokeboxOptionsListView.apply {
            layoutManager = GridLayoutManager(this@DashboardActivity, 2)
            adapter = optionsAdapter;
        }


        val newsList = arrayListOf<PokemonNews>(
            PokemonNews("New Pokemons arrived", "Today"),
            PokemonNews("New Pokemons arrived", "Today"),
            PokemonNews("New Pokemons arrived", "Today"),
            PokemonNews("New Pokemons arrived", "Today"),
            PokemonNews("New Pokemons arrived", "Today")
        );
        var pokemonNewsAdapter = PokemonNewsAdapter(this, newsList)

        viewBinding.pokemonNewsListView.apply {
            layoutManager = LinearLayoutManager(this@DashboardActivity, VERTICAL, false)
            adapter = pokemonNewsAdapter;
        }

        viewBinding.viewAllPokemonsBtn.setOnClickListener {
            val intent = Intent(this@DashboardActivity,MainActivity::class.java)
            startActivity(intent)
        }
    }
}