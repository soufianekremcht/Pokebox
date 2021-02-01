package com.soufianekre.pokebox.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class PokemonItemType(

    @Json(name = "slot")
    var slot :String? = "",
    @Json(name="type")
    var type : PokemonTypeInfo?=null){

}
@JsonClass(generateAdapter = true)
data class PokemonTypeInfo(
    @Json(name = "name")
    var name :String= ""){
}