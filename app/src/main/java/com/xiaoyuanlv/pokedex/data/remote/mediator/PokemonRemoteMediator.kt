package com.xiaoyuanlv.pokedex.data.remote.mediator

import androidx.annotation.experimental.Experimental
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.xiaoyuanlv.pokedex.data.local.database.PokedexDatabase
import com.xiaoyuanlv.pokedex.data.local.entity.PokemonEntity
import com.xiaoyuanlv.pokedex.data.remote.api.PokeApiService

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val api: PokeApiService,
    private val db: PokedexDatabase
) : RemoteMediator<Int, PokemonEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        val page = when(loadType) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> return MediatorResult.Success(true)
            LoadType.APPEND -> state.pages.size
        }

        return try {
            val response = api.getPokemonList(
                limit = state.config.pageSize,
                offset = page * state.config.pageSize
            )

            db.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    db.pokemonDao().clearAll()
                }

                db.pokemonDao().insertAll(
                    response.results.map {
                        PokemonEntity(it.name, it.url)
                    }
                )
            }

            MediatorResult.Success(response.results.isEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

}