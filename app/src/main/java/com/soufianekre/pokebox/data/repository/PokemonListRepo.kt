package com.soufianekre.pokebox.data.repository

import com.soufianekre.pokebox.PokeboxApp
import com.soufianekre.pokebox.data.db.dao.PokemonDao
import com.soufianekre.pokebox.data.models.PokemonItem
import com.soufianekre.pokebox.data.network.service.PokemonApiService
import com.soufianekre.pokebox.helper.NetworkUtils
import com.soufianekre.pokebox.helper.rx_scheduler.RxSchedulerProvider
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class PokemonListRepo(var pokemonApiService: PokemonApiService, var pokemonDao: PokemonDao) : Repo() {


    fun fetchPokemonItems(page :Int ,pageCount :Int): Flowable<List<PokemonItem>> {
        // One Way
        return Flowable.concatArray(

            Flowable.defer {
                if (NetworkUtils.isInternetAvailable(PokeboxApp.getInstance()))
                    fetchPokemonItemFromNetwork(pageCount,page).subscribeOn(Schedulers.io())
                else
                    Flowable.empty<List<PokemonItem>>()
            },
            fetchDataOffline())
    }

    private fun fetchPokemonItemFromNetwork(pageCount: Int,page :Int): Flowable<List<PokemonItem>> {
        var offset = page*pageCount;
        return pokemonApiService
            .getPokemonResponse(pageCount,offset = offset)
            .map {
                it.body()?.results
            }.flatMap {
                pokemonDao.insertPokemons(it)
                Flowable.just(it)
            }
    }

    private fun fetchDataOffline() : Flowable<List<PokemonItem>>{
        return pokemonDao.getPokemonList().subscribeOn(Schedulers.io())
    }

}