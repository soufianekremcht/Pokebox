package com.soufianekre.pokebox.data.repository

import com.soufianekre.pokebox.PokeboxApp
import com.soufianekre.pokebox.data.db.dao.PokemonInfoDao
import com.soufianekre.pokebox.data.models.PokemonItemInfo
import com.soufianekre.pokebox.data.network.PokemonApiService
import com.soufianekre.pokebox.helper.NetworkUtils
import com.soufianekre.pokebox.helper.rx_scheduler.RxScheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class PokemonDetailRepo(var pokemonApiService: PokemonApiService, var pokemonInfoDao: PokemonInfoDao
        ,var rxScheduler: RxScheduler) : Repo() {


    fun getPokemonInfo(name: String): Single<PokemonItemInfo> {
        var pokemonInfo = pokemonInfoDao.getPokemonInfo(name)

        // First Way
        return Single.ambArray(loadDataFromNetwork(name), loadDataOffline(name))


        // Seconde Way
        /*
        return if (!NetworkUtils.isNetworkConnected(PokeboxApp.getInstance())) {
            loadDataOffline(name)

        } else {
            loadDataFromNetwork(name)
        }

         */
    }

    private fun loadDataFromNetwork(name: String): Single<PokemonItemInfo> {
        return pokemonApiService.fetchPokemonInfo(name)
            .map {
                it.body()
            }.flatMap {
                pokemonInfoDao.insertPokemonInfo(it)
                Single.just(it)
            }
    }

    private fun loadDataOffline(name: String): Single<PokemonItemInfo> {
        val pokemonInfoDoa = pokemonInfoDao
        return pokemonInfoDoa.getPokemonInfo(name).subscribeOn(rxScheduler.IO())
    }

}