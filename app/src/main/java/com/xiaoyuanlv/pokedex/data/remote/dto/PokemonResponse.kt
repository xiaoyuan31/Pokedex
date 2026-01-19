package com.xiaoyuanlv.pokedex.data.remote.dto


data class PokemonResponse (
    val results: List<PokemonResult>
)

data class PokemonResult (
    val name: String,
    val url: String
)