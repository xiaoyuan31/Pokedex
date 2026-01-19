package com.xiaoyuanlv.pokedex.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xiaoyuanlv.pokedex.data.local.dao.PokemonDao
import com.xiaoyuanlv.pokedex.data.local.entity.PokemonEntity

@Database(
    entities = [PokemonEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PokedexDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}