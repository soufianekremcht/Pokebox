package com.soufianekre.pokebox.persistance



import com.soufianekre.pokebox.data.db.dao.PokemonDao
import com.soufianekre.pokebox.data.models.PokemonItem
import com.soufianekre.pokebox.repository.TestRxSchedulersProvider
import com.soufianekre.pokebox.test_common.MyMockUtil.mockPokemonList
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
import junit.framework.Assert.assertEquals
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
class PokemonDaoTest : LocalDatabase() {

    private lateinit var pokemonDao: PokemonDao

    @Before
    fun init() {
        pokemonDao = db.pokemonDao()
    }

    @Test
    fun insertAndLoadPokemonListTest() {

        val testSubscriber : TestSubscriber<List<PokemonItem>> = TestSubscriber()
        val testObserver = TestObserver<Unit>()
        val mockDataList = mockPokemonList()
        pokemonDao.insertPokemons(mockDataList)
            .observeOn(TestRxSchedulersProvider.IO())
            .subscribeOn(TestRxSchedulersProvider.UI()!!)
            .subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertNoErrors()

    }


    @Test
    fun loadPokemonListTest(){
        val loadFromDB = pokemonDao.getPokemonList()

        val testSubscriber : TestSubscriber<List<PokemonItem>> = TestSubscriber()
        val mockDataList = mockPokemonList()

        assertEquals(loadFromDB.toString(), mockDataList.toString())

        loadFromDB.observeOn(TestRxSchedulersProvider.IO())
            .subscribeOn(TestRxSchedulersProvider.UI()!!)
            .subscribe(testSubscriber)

        testSubscriber.assertComplete()
        testSubscriber.assertNoErrors()

        assertEquals(testSubscriber.values()[0][0].name,"bulbasaur")
        assertEquals(testSubscriber.values()[0][0].url,"https://pokeapi.co/api/v2/pokemon/1/")
    }
}
