package com.xiaoyuanlv.pokedex.data.remote.api

import com.xiaoyuanlv.pokedex.data.remote.dto.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokeApiService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): PokemonResponse

}