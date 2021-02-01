package com.soufianekre.pokebox

import com.soufianekre.pokebox.viewmodel.PokemonListModelTest
import com.soufianekre.pokebox.viewmodel.PokemonDetailViewModelTest
import org.junit.runner.RunWith
import org.junit.runners.Suite


@RunWith(Suite::class)
@Suite.SuiteClasses(PokemonDetailViewModelTest::class, PokemonListModelTest::class)
class PokeboxTestSuites