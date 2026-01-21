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
        imageUrl = PokemonUtils.getImageUrl(detail.id),
        height = detail.height,
        weight =  detail.weight,
        base_experience = detail.base_experience,
        hp = detail.stats.find { it.stat.name == "hp" }?.base_stat ?: 0,
        attack = detail.stats.find { it.stat.name == "attack" }?.base_stat ?: 0,
        defense = detail.stats.find { it.stat.name == "defense" }?.base_stat ?: 0,
        speed = detail.stats.find { it.stat.name == "speed" }?.base_stat ?: 0,
        specialAttack = detail.stats.find { it.stat.name == "special-attack" }?.base_stat ?: 0,
        specialDefense = detail.stats.find { it.stat.name == "special-defense" }?.base_stat ?: 0,
        frontImage = detail.sprites.front_default
    )

}