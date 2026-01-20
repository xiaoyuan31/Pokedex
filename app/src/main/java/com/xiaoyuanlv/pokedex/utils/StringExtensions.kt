package com.xiaoyuanlv.pokedex.utils

fun String.extractPokemonId(): Int {
    return trimEnd('/')
        .substringAfterLast('/')
        .toInt()
}