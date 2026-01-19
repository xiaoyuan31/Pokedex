package com.xiaoyuanlv.pokedex.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xiaoyuanlv.pokedex.data.local.entity.PokemonEntity

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon ORDER BY name ASC")
    fun pagingSource(): PagingSource<Int ,PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<PokemonEntity>)

    @Query("DELETE FROM pokemon")
    suspend fun clearAll()
}