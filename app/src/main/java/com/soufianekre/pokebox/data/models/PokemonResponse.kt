package com.soufianekre.pokebox.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class PokemonResponse(
    @Json(name = "count")
    var count : Int = 0,
    @Json(name= "next")
    var next : String?,
    @Json(name = "previous")
    var previous : String?,
    @Json(name = "results")
    var results : List<PokemonItem>
)