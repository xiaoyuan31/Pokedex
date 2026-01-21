package com.xiaoyuanlv.pokedex.ui.pokemondetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xiaoyuanlv.pokedex.data.local.entity.PokemonEntity
import com.xiaoyuanlv.pokedex.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _pokemonDetail = MutableLiveData<PokemonEntity?>()
    val pokemonDetail: LiveData<PokemonEntity?> = _pokemonDetail

    fun loadPokemon(id: Int) {
        viewModelScope.launch {
            val pokemon = repository.getPokemonById(id) // OK
            _pokemonDetail.postValue(pokemon)
        }
    }
}