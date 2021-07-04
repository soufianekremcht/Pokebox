package com.soufianekre.pokebox.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.soufianekre.pokebox.data.db.dao.PokemonInfoDao
import com.soufianekre.pokebox.data.models.PokemonItemInfo
import com.soufianekre.pokebox.data.network.PokemonApiService
import com.soufianekre.pokebox.data.repository.PokemonDetailRepo
import com.soufianekre.pokebox.test_common.MyMockUtil
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class RepoPokemonDetailTest {

    private lateinit var repository: PokemonDetailRepo

    private val service: PokemonApiService = mock()
    private val pokemonInfoDao: PokemonInfoDao = mock()


    @Before
    fun setup() {
        repository = PokemonDetailRepo(service, pokemonInfoDao, TestRxSchedulersProvider)
    }

    @After
    fun onFinish() {

    }

    @Test
    fun test_fetching_pokemon_info_network() {
        val testObserver: TestObserver<PokemonItemInfo> = TestObserver()
        setupNetworkMocks()

        repository.getPokemonInfo("bulbasaur")
            .subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        testObserver.dispose();

    }

    @Test
    fun test_fetching_pokemon_info_db() {
        val testObserver: TestObserver<PokemonItemInfo> = TestObserver()

        setupDbMocks()
        repository.getPokemonInfo("bulbasaur")
            .subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        testObserver.dispose();

    }

    private fun setupDbMocks() {
        val mockData = MyMockUtil.mockPokemonInfo()
        whenever(pokemonInfoDao.getPokemonInfo(name = "bulbasaur"))
            .thenReturn(Single.just(mockData))
        whenever(service.fetchPokemonInfo(name = "bulbasaur"))
            .thenReturn(Single.just(Response.success(mockData)))

    }

    private fun setupNetworkMocks() {
        val mockData = MyMockUtil.mockPokemonInfo()
        whenever(pokemonInfoDao.getPokemonInfo(name = "bulbasaur"))
            .thenReturn(null)
        whenever(service.fetchPokemonInfo(name = "bulbasaur"))
            .thenReturn(Single.just(Response.success(mockData)))
    }

}