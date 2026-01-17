package com.xiaoyuanlv.pokedex.di

import android.content.Context
import androidx.room.Room
import com.xiaoyuanlv.pokedex.data.local.PokedexDatabase
import com.xiaoyuanlv.pokedex.data.local.dao.PokemonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): PokedexDatabase =
        Room.databaseBuilder(
            context,
            PokedexDatabase::class.java,
            "pokedex.db"
        ).build()

    @Provides
    fun provideDao(db: PokedexDatabase) : PokemonDao =
        db.pokemonDao()
}