package com.soufianekre.pokebox.data.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@Entity(tableName = "pokemon")
@JsonClass(generateAdapter = true)

data class PokemonItem(
    @PrimaryKey
    @Json(name = "name")
    var name: String = "",
    @Json(name = "url")
    var url: String = ""
) : Parcelable{

    @Ignore
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    fun getImageUrl(): String {
        val index = url!!.split("/".toRegex()).dropLast(1).last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PokemonItem> {
        override fun createFromParcel(parcel: Parcel): PokemonItem {
            return PokemonItem(parcel)
        }

        override fun newArray(size: Int): Array<PokemonItem?> {
            return arrayOfNulls(size)
        }
    }

}