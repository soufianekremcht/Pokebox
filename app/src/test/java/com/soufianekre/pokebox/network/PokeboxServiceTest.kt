package com.soufianekre.pokebox.network

import com.nhaarman.mockitokotlin2.mock
import com.soufianekre.pokebox.data.models.PokemonItemInfo
import com.soufianekre.pokebox.data.models.PokemonResponse
import com.soufianekre.pokebox.data.network.PokeboxClient
import com.soufianekre.pokebox.data.network.PokemonApiService
import com.soufianekre.pokebox.repository.TestRxSchedulersProvider
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.TestScheduler
import io.reactivex.subscribers.TestSubscriber
import junit.framework.Assert.assertEquals
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.io.IOException
import java.util.*

class PokeboxServiceTest : ApiAbstract<PokemonApiService>() {

    private lateinit var service: PokemonApiService



    @Before
    fun initService() {
        service = createService(PokemonApiService::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun fetchPokemonListFromNetworkTest() {
        val testObserver = TestSubscriber<Response<PokemonResponse>>()

        enqueueResponse("/PokemonResponse.json")
        val response = service.fetchPokemonResponse(20,0)
        response.observeOn(TestRxSchedulersProvider.IO())
            .subscribeOn(TestRxSchedulersProvider.UI()!!)
            .subscribe(testObserver)

        testObserver.assertComplete()
        assertEquals(testObserver.values()[0].body()!!.count,964)
        assertEquals(testObserver.values()[0].body()!!.results[0].url,"https://pokeapi.co/api/v2/pokemon/1/")
        mockWebServer.takeRequest()

        /*
        assertThat(responseBody.results[0].name, `is`("bulbasaur"))
        assertThat(responseBody.results[0].url, `is`("https://pokeapi.co/api/v2/pokemon/1/"))

         */

    }

    @Throws(IOException::class)
    @Test

    fun fetchPokemonInfoFromNetworkTest() {
        val testObserver = TestObserver<Response<PokemonItemInfo>>();

        enqueueResponse("/Bulbasaur.json")
        val response = service.fetchPokemonInfo("bulbasaur")

        response.observeOn(TestRxSchedulersProvider.IO())
            .subscribeOn(TestRxSchedulersProvider.UI())
            .subscribe(testObserver)

    }

}
