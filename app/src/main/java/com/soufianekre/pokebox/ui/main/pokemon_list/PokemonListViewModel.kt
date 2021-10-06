package com.soufianekre.pokebox.ui.main.pokemon_list

import androidx.lifecycle.MutableLiveData
import com.soufianekre.pokebox.PokeboxApp
import com.soufianekre.pokebox.data.db.AppDatabase
import com.soufianekre.pokebox.data.models.PokemonItem
import com.soufianekre.pokebox.data.network.RestProvider
import com.soufianekre.pokebox.data.repository.PokemonListRepo
import com.soufianekre.pokebox.helper.RxHelper
import com.soufianekre.pokebox.helper.rx_scheduler.RxSchedulerProvider
import com.soufianekre.pokebox.ui.base.BaseViewModel
import timber.log.Timber

class PokemonListViewModel : BaseViewModel() {

    private val pokemonListRepo: PokemonListRepo =
        PokemonListRepo(
            RestProvider.getPokemonService(),
            AppDatabase.getInstance(PokeboxApp.getInstance()).pokemonDao(),
            RxSchedulerProvider
        )

    init {
        fetchPokemons(20, 0)
    }

    var pokemonListInfo: MutableLiveData<List<PokemonItem>> = MutableLiveData()

    fun fetchPokemons(pageSize: Int,page: Int) {
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