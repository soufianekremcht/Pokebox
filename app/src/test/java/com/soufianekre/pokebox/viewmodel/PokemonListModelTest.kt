package com.soufianekre.pokebox.viewmodel

import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.soufianekre.pokebox.data.db.dao.PokemonDao
import com.soufianekre.pokebox.data.repository.PokemonListRepo
import com.soufianekre.pokebox.ui.main.MainViewModel
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import java.util.concurrent.TimeUnit


class PokemonListModelTest {
    private lateinit var mainViewModel : MainViewModel


    private val mockPokemonDao : PokemonDao = mock()
    private lateinit var pokemonListRepo : PokemonListRepo

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        // init repo
    }


    @Test
    fun pokemonList_isLoaded(){

        val testScheduler = TestScheduler()
        mockPokemonDao.getPokemonList()
            .subscribeOn(testScheduler)
            .observeOn(testScheduler)
            .subscribe()
        setupMocks()
        verify(mockPokemonDao, atLeastOnce()).getPokemonList()



        // Using Test Observer
        // Read carefully, this is very important
        val testObserver: TestObserver<Int> = Observable.just(1)
            .subscribeOn(testScheduler)
            .observeOn(testScheduler)
            .test()

        testObserver.assertNotTerminated() // not compulsory, but STRONGLY recommended
            .assertNoErrors()
            .assertValueCount(0) // "time" hasn't started so no value expected


        testScheduler.advanceTimeBy(1L, TimeUnit.SECONDS)
        testObserver.assertValueCount(1) // 1 value expected after the initial delay of 1 second

        testScheduler.advanceTimeBy(5L, TimeUnit.SECONDS)
        testObserver.assertValueCount(2) // 1st interval, 1 more value expected

        testScheduler.advanceTimeBy(5L, TimeUnit.SECONDS)
        testObserver.assertValueCount(3) // 2nd interval, 1 more value

        // Clean up resources, not compulsory, but STRONGLY recommended
        testObserver.dispose()

    }

    private fun setupMocks() {
        //var pokemonList : List<PokemonItem> = arrayListOf(PokemonItem("1","httpdfd"))
        //whenever(mockPokemonDao.getPokemonList()).thenReturn(pokemonListResponse)
    }
}