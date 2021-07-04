package com.soufianekre.pokebox.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.soufianekre.pokebox.data.db.dao.PokemonDao
import com.soufianekre.pokebox.data.models.PokemonItem
import com.soufianekre.pokebox.data.models.PokemonResponse
import com.soufianekre.pokebox.data.network.PokemonApiService
import com.soufianekre.pokebox.data.repository.PokemonListRepo
import com.soufianekre.pokebox.rules.TestSchedulerRule
import com.soufianekre.pokebox.test_common.MyMockUtil
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import io.reactivex.subscribers.TestSubscriber
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import java.util.concurrent.TimeUnit

class RepoPokemonListTest {

    private lateinit var repository: PokemonListRepo
    private val service: PokemonApiService = mock()
    private val pokemonDao: PokemonDao = mock()

//    @Rule
//    public var testSchedulerTestRule : TestSchedulerRule = TestSchedulerRule()

    @Before
    fun setup(){
            repository = PokemonListRepo(service,pokemonDao,TestRxSchedulersProvider)
    }

    @After
    fun finish(){

    }


    @Test
    public fun test_fetching_pokemon_list_db(){


        // test subscriber for Flowable and test observers for observables
        val subscriber: TestSubscriber<List<PokemonItem>> = TestSubscriber()

        setupDBMocks()

        // synchronously wait
        /**
        var results : Iterable<List<PokemonItem>> = repository.fetchPokemonItems(10,0)
            .subscribeOn(Schedulers.io())
            .blockingIterable();
        **/

        //
        repository.fetchPokemonItems(10,0)

            .subscribe(subscriber)

        subscriber.assertComplete()
        subscriber.assertNoErrors()
        subscriber.assertValueCount(1)
        //testScheduler.advanceTimeBy(1,TimeUnit.SECONDS)
        //subscriber.awaitTerminalEvent(2, TimeUnit.SECONDS);

        /*

        subscriber.assertNotTerminated() // not compulsory, but STRONGLY recommended
            .assertNoErrors()
            .assertValueCount(0) // "time" hasn't started so no value expected

         */


        //testScheduler.advanceTimeBy(1L, TimeUnit.SECONDS)
        subscriber.assertValueCount(1) // 1 value expected after the initial delay of 1 second

        //testScheduler.advanceTimeBy(5L, TimeUnit.SECONDS)
        subscriber.assertValueCount(2) // 1st interval, 1 more value expected

        //testScheduler.advanceTimeBy(5L, TimeUnit.SECONDS)
        subscriber.assertValueCount(3) // 2nd interval, 1 more value

        // Clean up resources, not compulsory, but STRONGLY recommended
        subscriber.dispose()

    }

    @Test
    fun test_fetching_pokemon_list_network(){
        // manipulate time while the observable is active
        val subscriber: TestSubscriber<List<PokemonItem>> = TestSubscriber()

        setupNetworkMocks()

        repository.fetchPokemonItems(10,0)
            .subscribe(subscriber)

        subscriber.assertComplete()
        subscriber.assertNoErrors()
        subscriber.assertValueCount(1)
        //testScheduler.advanceTimeBy(1,TimeUnit.SECONDS)
        //subscriber.awaitTerminalEvent(2, TimeUnit.SECONDS);


        /*
        subscriber.assertNotTerminated() // not compulsory, but STRONGLY recommended
            .assertNoErrors()
            .assertValueCount(0) // "time" hasn't started so no value expected

         */


        //testScheduler.advanceTimeBy(1L, TimeUnit.SECONDS)
        subscriber.assertValueCount(1) // 1 value expected after the initial delay of 1 second

        //testScheduler.advanceTimeBy(5L, TimeUnit.SECONDS)
        subscriber.assertValueCount(2) // 1st interval, 1 more value expected

        //testScheduler.advanceTimeBy(5L, TimeUnit.SECONDS)
        subscriber.assertValueCount(3) // 2nd interval, 1 more value

        // Clean up resources, not compulsory, but STRONGLY recommended
        subscriber.dispose()


    }

    private fun setupDBMocks() {
        val mockPokemonList = MyMockUtil.mockPokemonList()
        val mockResponse = PokemonResponse(count = 984, next = null, previous = null, results = mockPokemonList)

        whenever(pokemonDao.getPokemonList()).thenReturn(
            Flowable.just(mockPokemonList))

        whenever(service.fetchPokemonResponse(10,0)).thenReturn(
            Flowable.empty()
        )
    }

    private fun setupNetworkMocks(){
        val mockPokemonList = MyMockUtil.mockPokemonList()
        val mockResponse = PokemonResponse(count = 984, next = null, previous = null, results = mockPokemonList)

        whenever(pokemonDao.getPokemonList()).thenReturn(
            Flowable.empty()
        )
        whenever(service.fetchPokemonResponse(10,0)).thenReturn(
            Flowable.just(Response.success(mockResponse))
        )
    }
}