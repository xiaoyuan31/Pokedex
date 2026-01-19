package com.xiaoyuanlv.pokedex.utils

object PokemonUtils {
    fun getPokemonId(url: String): Int {
        return url.trimEnd('/')
            .split("/")
            .last()
            .toInt()
    }

    fun getImageUrl(id: Int): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
    }
}