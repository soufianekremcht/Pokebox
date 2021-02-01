package com.soufianekre.pokebox.data.repository

import com.soufianekre.pokebox.PokeboxApp
import com.soufianekre.pokebox.data.db.dao.PokemonInfoDao
import com.soufianekre.pokebox.data.models.PokemonItemInfo
import com.soufianekre.pokebox.data.network.service.PokemonApiService
import com.soufianekre.pokebox.helper.NetworkUtils
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class PokemonDetailRepo(var pokemonApiService: PokemonApiService, var pokemonInfoDao: PokemonInfoDao) : Repo() {


    fun getPokemonInfo(name: String): Single<PokemonItemInfo> {
        return if (NetworkUtils.isNetworkConnected(PokeboxApp.getInstance())) {
            loadDataFromNetwork(name)
        } else {
            loadDataOffline(name)
        }
    }

    private fun loadDataFromNetwork(name: String): Single<PokemonItemInfo> {
        return pokemonApiService.getPokemon(name)
            .map {
                it.body()
            }.flatMap {
                pokemonInfoDao.insertPokemonInfo(it)
                Single.just(it)
            }
    }

    private fun loadDataOffline(name: String): Single<PokemonItemInfo> {
        val pokemonInfoDoa = pokemonInfoDao
        return pokemonInfoDoa.getPokemonInfo(name).subscribeOn(Schedulers.io())
    }

}