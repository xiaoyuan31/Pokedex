package com.xiaoyuanlv.pokedex.data.mapper

import com.xiaoyuanlv.pokedex.data.local.entity.PokemonEntity
import com.xiaoyuanlv.pokedex.data.remote.dto.PokemonResultDto
import com.xiaoyuanlv.pokedex.utils.PokemonUtils
import com.xiaoyuanlv.pokedex.utils.extractPokemonId

fun PokemonResultDto.toEntity() : PokemonEntity {
    val id = url.extractPokemonId()

    return PokemonEntity(
        id = id,
        name = name,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
    )
}