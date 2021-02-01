package com.soufianekre.pokebox.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class PokemonResults(
    @Json(name = "count")
    var count : Int = 0,
    @Json(name= "next")
    var next : String?,
    @Json(name = "previous")
    var pervious : String?,
    @Json(name = "results")
    var results : List<PokemonItem>
)