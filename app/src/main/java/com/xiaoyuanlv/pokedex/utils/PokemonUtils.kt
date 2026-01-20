package com.xiaoyuanlv.pokedex.utils

import android.graphics.Color

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


object TypeColor {
    fun getColor(type: String): Int {
        return when(type.lowercase()) {
            "fire" -> Color.parseColor("#F08030")
            "water" -> Color.parseColor("#6890F0")
            "grass" -> Color.parseColor("#78C850")
            "electric" -> Color.parseColor("#F8D030")
            "ice" -> Color.parseColor("#98D8D8")
            "fighting" -> Color.parseColor("#C03028")
            "poison" -> Color.parseColor("#A040A0")
            "ground" -> Color.parseColor("#E0C068")
            "flying" -> Color.parseColor("#A890F0")
            "psychic" -> Color.parseColor("#F85888")
            "bug" -> Color.parseColor("#A8B820")
            "rock" -> Color.parseColor("#B8A038")
            "ghost" -> Color.parseColor("#705898")
            "dragon" -> Color.parseColor("#7038F8")
            "dark" -> Color.parseColor("#705848")
            "steel" -> Color.parseColor("#B8B8D0")
            "fairy" -> Color.parseColor("#EE99AC")
            else -> Color.LTGRAY
        }
    }
}