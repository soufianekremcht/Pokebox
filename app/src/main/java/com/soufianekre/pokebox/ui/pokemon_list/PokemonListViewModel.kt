package com.soufianekre.pokebox.ui.pokemon_list

import androidx.lifecycle.MutableLiveData
import com.soufianekre.pokebox.PokeboxApp
import com.soufianekre.pokebox.data.db.AppDatabase
import com.soufianekre.pokebox.data.models.PokemonItem
import com.soufianekre.pokebox.data.network.RestProvider
import com.soufianekre.pokebox.data.repository.PokemonListRepo
import com.soufianekre.pokebox.helper.RxHelper
import com.soufianekre.pokebox.ui.base.BaseViewModel
import timber.log.Timber

class PokemonListViewModel : BaseViewModel() {

    private val pokemonListRepo: PokemonListRepo =
        PokemonListRepo(
            RestProvider.getPokemonService(),
            AppDatabase.getInstance(PokeboxApp.getInstance()).pokemonDao()
        )

    init {
        fetchPokemons(0, 20)
    }

    var pokemonListInfo: MutableLiveData<List<PokemonItem>> = MutableLiveData()

    fun fetchPokemons(page: Int, pageSize: Int) {
        compositeDisposable.add(
            RxHelper
                .getFlowable(pokemonListRepo.fetchPokemonItems(page,pageSize))
                .subscribe({
                    pokemonListInfo.postValue(it!!)
                }, {
                    Timber.e("Error Thrown : %s", it.localizedMessage)
                })
        )
    }
}