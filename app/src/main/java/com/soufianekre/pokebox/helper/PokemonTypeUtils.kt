package com.soufianekre.pokebox.helper

import com.soufianekre.pokebox.R

object PokemonTypeUtils{

    fun getTypeColor(type: String): Int {
        return when (type) {
            "fighting" -> R.color.material_red_800
            "flying" -> R.color.flying
            "poison" -> R.color.poison
            "ground" -> R.color.material_brown_600
            "rock" -> R.color.rock
            "bug" -> R.color.gray_21
            "ghost" -> R.color.ghost
            "steel" -> R.color.steel
            "fire" -> R.color.fire
            "water" -> R.color.water
            "grass" -> R.color.material_green_700
            "electric" -> R.color.electric
            "psychic" -> R.color.psychic
            "ice" -> R.color.ice
            "dragon" -> R.color.material_red_accent_400
            "fairy" -> R.color.fairy
            "dark" -> R.color.material_light_black
            else -> R.color.gray_21
        }
    }
}