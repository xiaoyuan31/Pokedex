package com.xiaoyuanlv.pokedex.data.remote.dto

data class PokemonDetailResponseDto(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val types: List<PokemonTypeSlot>,
    val sprites: SpritesDto,
    val height: Int,
    val weight: Int,
    val stats: List<PokemonStat>,
    val base_experience: Int
)

data class PokemonTypeSlot(
    val slot: Int,
    val type: PokemonType
)

data class PokemonType(
    val name: String,
    val url: String
)

data class SpritesDto(val front_default: String)

data class PokemonStat(
    val base_stat: Int,
    val effort: Int,
    val stat: PokemonStatName
)

data class PokemonStatName(
    val name: String,
    val url: String
)