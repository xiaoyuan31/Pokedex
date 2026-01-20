package com.xiaoyuanlv.pokedex.data.mapper

import com.xiaoyuanlv.pokedex.data.local.entity.PokemonEntity
import com.xiaoyuanlv.pokedex.data.remote.dto.PokemonDetailResponseDto
import com.xiaoyuanlv.pokedex.data.remote.dto.PokemonResultDto
import com.xiaoyuanlv.pokedex.utils.PokemonUtils
import com.xiaoyuanlv.pokedex.utils.extractPokemonId

fun PokemonResultDto.toEntity(detail: PokemonDetailResponseDto) : PokemonEntity {

    return PokemonEntity(
        id = detail.id,
        name = detail.name,
        types = detail.types.map { it.type.name },
        imageUrl = PokemonUtils.getImageUrl(detail.id)
    )

}