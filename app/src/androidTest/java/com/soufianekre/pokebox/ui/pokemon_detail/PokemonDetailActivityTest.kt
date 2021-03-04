package com.soufianekre.pokebox.ui.pokemon_detail

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions

import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.soufianekre.pokebox.R
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PokemonDetailActivityTest{

    @Rule
    val activityRule : ActivityScenarioRule<PokemonDetailActivity>
            = ActivityScenarioRule(PokemonDetailActivity::class.java)

    @Before
    fun setup(){
    }

    @Test
    fun test_pokemonName(){
        onView(withId(R.id.pokemon_name)).perform(click()).check(ViewAssertions.matches(withText("Pokemon_name")))
    }

    @After
    fun tearDown(){

    }
}