package com.xiaoyuanlv.pokedex.data.remote.dto

class PokemonListResponseDto(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonResultDto>
)