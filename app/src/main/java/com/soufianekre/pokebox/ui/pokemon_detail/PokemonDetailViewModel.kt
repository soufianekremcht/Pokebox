package com.soufianekre.pokebox.ui.pokemon_detail

import androidx.lifecycle.MutableLiveData
import com.soufianekre.pokebox.PokeboxApp
import com.soufianekre.pokebox.data.db.AppDatabase
import com.soufianekre.pokebox.data.models.PokemonItemInfo
import com.soufianekre.pokebox.data.network.RestProvider
import com.soufianekre.pokebox.data.repository.PokemonDetailRepo
import com.soufianekre.pokebox.helper.RxHelper
import com.soufianekre.pokebox.ui.base.BaseViewModel
import timber.log.Timber

class PokemonDetailViewModel() : BaseViewModel() {

    var pokemonDetailRepo: PokemonDetailRepo = PokemonDetailRepo(
        RestProvider.getPokemonService(),
        AppDatabase.getInstance(PokeboxApp.getInstance()).pokemonInfoDao()
    )
    var pokemonInfoLiveData: MutableLiveData<PokemonItemInfo?> = MutableLiveData()

    init {
        Timber.e("pokemon Detail View Model has been initialized")
    }


    fun fetchPokemonInfo(name: String) {
        compositeDisposable.add(
            RxHelper.getSingle(pokemonDetailRepo.getPokemonInfo(name))
                .subscribe({
                    pokemonInfoLiveData.postValue(it)
                }, {
                    pokemonInfoLiveData.postValue(PokemonItemInfo())
                    Timber.e("Error Occurred %s", it?.localizedMessage)

                })
        )
    }


}