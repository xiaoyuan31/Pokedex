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
import com.xiaoyuanlv.pokedex.utils.PokemonUtils
import com.xiaoyuanlv.pokedex.utils.extractPokemonId

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val api: PokeApiService,
    private val db: PokedexDatabase
) : RemoteMediator<Int, PokemonEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {

        val offset = when (loadType) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> return MediatorResult.Success(true)
            LoadType.APPEND -> state.pages.sumOf { it.data.size }
        }

        return try {
            val response = api.getPokemonList(
                limit = 20,
                offset = offset
            )

            db.withTransaction {

                if(loadType == LoadType.REFRESH) {
                    db.pokemonDao().clearAll()
                }

                val entities = response.results.mapNotNull  { it ->
                    runCatching {
                        val detail = api.getPokemonDetail(it.url.extractPokemonId())
                        it.toEntity(detail = detail)
                    }.getOrNull()
                }

                db.pokemonDao().insertAll(entities)
            }

            MediatorResult.Success(
                endOfPaginationReached = response.results.size < state.config.pageSize
            )
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

}