package com.xiaoyuanlv.pokedex.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.xiaoyuanlv.pokedex.data.local.dao.PokemonDao
import com.xiaoyuanlv.pokedex.data.local.database.PokedexDatabase
import com.xiaoyuanlv.pokedex.data.local.entity.PokemonEntity
import com.xiaoyuanlv.pokedex.data.remote.api.PokeApiService
import com.xiaoyuanlv.pokedex.data.remote.mediator.PokemonRemoteMediator
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val api: PokeApiService,
    private val db: PokedexDatabase
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getPokemonPaging() = Pager(
        config = PagingConfig(pageSize = 20),
        remoteMediator = PokemonRemoteMediator(api, db),
        pagingSourceFactory = { db.pokemonDao().pagingSource() }
    ).flow

}