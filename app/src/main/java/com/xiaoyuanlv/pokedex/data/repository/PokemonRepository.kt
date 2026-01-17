package com.xiaoyuanlv.pokedex.data.repository

import com.xiaoyuanlv.pokedex.data.local.dao.PokemonDao
import com.xiaoyuanlv.pokedex.data.local.entity.PokemonEntity
import com.xiaoyuanlv.pokedex.data.model.Pokemon
import com.xiaoyuanlv.pokedex.data.remote.PokeApiService
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val api: PokeApiService,
    private val dao: PokemonDao
) {
    suspend fun getPokemon() : List<Pokemon> {
        return try {
            val remote = api.getPokemonList().results
            dao.insertAll(remote.map {
                PokemonEntity(it.name, it.url)
            })
            remote
        } catch (e: Exception) {
            dao.getAll().map {
                Pokemon(it.name, it.url)
            }
        }
    }
}