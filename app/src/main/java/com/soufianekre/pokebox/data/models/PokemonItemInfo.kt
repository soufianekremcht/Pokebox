package com.soufianekre.pokebox.data.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable
import kotlin.random.Random.Default.nextInt

@Entity(tableName = "pokemon_info")
@JsonClass(generateAdapter = true)
data class PokemonItemInfo(
    @PrimaryKey
    @Json(name = "id")
    var pokemonId: Int = 0,
    @Json(name = "name")
    var name: String? = "",
    @Json(name = "weight")
    var weight: Int = 0,
    @Json(name = "height")
    var height: Int = 0,
    @Json(name = "base_experience")
    var experience: Int = 0,
    @Json(name = "stats")
    var stats: List<PokemonItemStat>? = ArrayList(),
    @Json(name = "types")
    var types: List<PokemonItemType>? = ArrayList(),
    @Json(name = "sprites")
    var sprites: PokemonItemSprite? = null,
    var hp: Int = nextInt(maxHp),
    var attack: Int = nextInt(maxAttack),
    var defense: Int = nextInt(maxDefense),
    var speed: Int = nextInt(maxSpeed),
    var exp: Int = nextInt(maxExp)

) : Serializable{

    fun getIdString(): String = String.format("#%03d", pokemonId)
    fun getWeightString(): String = String.format("%.1f KG", weight.toFloat() / 10)
    fun getHeightString(): String = String.format("%.1f M", height.toFloat() / 10)
    fun getHpString(): String = "$hp/$maxHp"
    fun getAttackString(): String = "$attack/$maxAttack"
    fun getDefenseString(): String = "$defense/$maxDefense"
    fun getSpeedString(): String = "$speed/$maxSpeed"
    fun getExpString(): String = "$exp/$maxExp"


    companion object {
        const val maxHp = 300
        const val maxAttack = 300
        const val maxDefense = 300
        const val maxSpeed = 300
        const val maxExp = 1000
    }

    @JsonClass(generateAdapter = true)
    data class PokemonItemSprite(
        @Json(name = "front_default")
        var imageFrontDefault :String = "",
        @Json(name = "back_default")
        var imageBackDefault :String = ""
    ){




    }




}