package com.xiaoyuanlv.pokedex.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.xiaoyuanlv.pokedex.data.remote.dto.PokemonStat

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val imageUrl: String,
    val types: List<String>,
    val base_experience: Int,
    val height: Int,
    val weight: Int,
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val speed: Int,
    val specialAttack: Int,
    val specialDefense: Int,
    val frontImage: String
)