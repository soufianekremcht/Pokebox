package com.soufianekre.pokebox.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "pokemon")
@Parcelize
@JsonClass(generateAdapter = true)

data class PokemonItem(
    @PrimaryKey()
    @Json(name = "name")
    var name: String = "",
    @Json(name = "url")
    var url: String = ""
) : Parcelable {


    fun getImageUrl(): String {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
    }
}