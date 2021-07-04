package com.soufianekre.pokebox.data.repository

import com.soufianekre.pokebox.PokeboxApp
import com.soufianekre.pokebox.data.db.dao.PokemonDao
import com.soufianekre.pokebox.data.models.PokemonItem
import com.soufianekre.pokebox.data.network.PokemonApiService
import com.soufianekre.pokebox.helper.NetworkUtils
import com.soufianekre.pokebox.helper.rx_scheduler.RxScheduler
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class PokemonListRepo(var pokemonApiService: PokemonApiService, var pokemonDao: PokemonDao
    ,var rxScheduler: RxScheduler) : Repo() {


    fun fetchPokemonItems(pageCount :Int,page :Int ): Flowable<List<PokemonItem>> {

        // First Method
        /*
        return if (NetworkUtils.isNetworkConnected(PokeboxApp.getInstance())) {
            fetchPokemonItemFromNetwork(pageCount, page).subscribeOn(rxScheduler.IO()!!)
        }else {
            fetchDataOffline()

        }

         */

        // Second
        return Flowable.concatArrayDelayError(
            fetchPokemonItemFromNetwork(pageCount, page),fetchDataOffline())
            .firstElement()
            .toFlowable()


        /*
        return Flowable.concatArray(

            Flowable.defer {
                if (NetworkUtils.isInternetAvailable(PokeboxApp.getInstance()))
                else
                    Flowable.empty<List<PokemonItem>>()
            },
            fetchDataOffline())

         */
    }

    private fun fetchPokemonItemFromNetwork(pageCount: Int,page :Int): Flowable<List<PokemonItem>> {
        val offset = page*pageCount;
        return pokemonApiService
            .fetchPokemonResponse(pageCount,offset = offset)

            .map {
                it.body()?.results

            }.flatMap {
                Flowable.just(it)
            }.doOnNext{
                pokemonDao.insertPokemons(it)
            }
    }

    private fun insertPokemon(poke : PokemonItem){


    }

    private fun fetchDataOffline() : Flowable<List<PokemonItem>>{
        return pokemonDao.getPokemonList().subscribeOn(rxScheduler.IO()!!)
    }

}