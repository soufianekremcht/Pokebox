package com.soufianekre.pokebox.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable


@JsonClass(generateAdapter = true)
data class PokemonItemStat(
    @Json(name = "stat")
    var stat : PokemonStatInfo?= null,
    @Json(name = "effort")
    var effort:Int = 0,
    @Json(name = "base_stat")
    var baseStat :Int = 0):Serializable {



}
@JsonClass(generateAdapter = true)
data class PokemonStatInfo(
    @Json(name="stat")
    var name: String? =""): Serializable