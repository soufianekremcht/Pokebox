package com.soufianekre.pokebox.ui.pokemon_list


import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.soufianekre.pokebox.R
import com.soufianekre.pokebox.ui.main.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)

class PokemonListFragmentTest {

    private val POKEMON_IN_TEST: String = "bulbasaur"
    lateinit var mainActivity: MainActivity
    val LIST_ITEM_IN_TEST = 4

    @Before
    fun setUp() {
        //mainActivity = mock(MainActivity::class.java)

    }

    @After
    fun tearDown() {
    }

    @Test
    fun test_pokemons_data_visibility() {
        val bundle = Bundle()
        val scenario = launchFragmentInContainer<PokemonListFragment>(
            fragmentArgs = bundle
        )
        scenario.moveToState(Lifecycle.State.CREATED)

        onView(withId(R.id.pokemon_recycler_view)).check(
            matches(
                isDisplayed()
            )
        )

        //onView(withId(R.id.pokemon_recycler_view)).check()


    }

    @Test
    fun test_selectListItem_isDetailFragmentVisible() {

        // Click list item #LIST_ITEM_IN_TEST

        onView(withId(R.id.recycler_view))
            .perform(
                actionOnItemAtPosition<PokemonListAdapter.PokemonListViewHolder>(
                    LIST_ITEM_IN_TEST,
                    click()
                )
            )
        // Confirm nav to DetailFragment and display title
        onView(withId(R.id.pokemon_name)).check(matches(withText(POKEMON_IN_TEST)))
    }

    @Test
    fun test_backNavigation_toPokemonListFragment() {

        // Click list item #LIST_ITEM_IN_TEST
        onView(withId(R.id.recycler_view))
            .perform(actionOnItemAtPosition<PokemonListAdapter.PokemonListViewHolder>(LIST_ITEM_IN_TEST, click()))

        // Confirm nav to DetailFragment and display title
        onView(withId(R.id.pokemon_name)).check(matches(withText(POKEMON_IN_TEST)))


        pressBack()

        // Confirm MovieListFragment in view
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun test_pokemonListFragment_validateDirectorsList() {

        // Click list item #LIST_ITEM_IN_TEST
        onView(withId(R.id.recycler_view))
            .perform(actionOnItemAtPosition<PokemonListAdapter.PokemonListViewHolder>(LIST_ITEM_IN_TEST, click()))

        // Confirm nav to DetailFragment and display title

    }

    @Test
    fun test_navStarActorsFragment_validateActorsList() {

        // Click list item #LIST_ITEM_IN_TEST
        onView(withId(R.id.recycler_view))
            .perform(actionOnItemAtPosition<PokemonListAdapter.PokemonListViewHolder>(LIST_ITEM_IN_TEST, click()))

        // Confirm nav to DetailFragment and display title
        onView(withId(R.id.pokemon_name)).check(matches(withText(POKEMON_IN_TEST)))

        // Nav to DirectorsFragment
        //onView(withId(R.id.movie_star_actors)).perform(click())

    }
}