package com.xiaoyuanlv.pokedex.data.remote.mediator

import androidx.annotation.experimental.Experimental
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.xiaoyuanlv.pokedex.data.local.database.PokedexDatabase
import com.xiaoyuanlv.pokedex.data.local.entity.PokemonEntity
import com.xiaoyuanlv.pokedex.data.mapper.toEntity
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
        val offset = when(loadType) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> return MediatorResult.Success(true)
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()
                    ?: return MediatorResult.Success(endOfPaginationReached = true)
                lastItem.id
            }
        }

        return try {
            val response = api.getPokemonList(
                limit = state.config.pageSize,
                offset = offset
            )

            db.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    db.pokemonDao().clearAll()
                }

                val entities = response.results.map {
                    it.toEntity()
                }

                db.pokemonDao().insertAll(entities)
            }

            MediatorResult.Success(response.results.isEmpty())

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

}