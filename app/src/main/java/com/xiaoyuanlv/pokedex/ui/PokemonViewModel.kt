package com.xiaoyuanlv.pokedex.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xiaoyuanlv.pokedex.data.model.Pokemon
import com.xiaoyuanlv.pokedex.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val repository: PokemonRepository
)  : ViewModel() {

    private val _pokemon = MutableLiveData<List<Pokemon>>()
    val pokemon: LiveData<List<Pokemon>> = _pokemon

    fun loadPokemon() {
        viewModelScope.launch {
            _pokemon.value = repository.getPokemon()
        }
    }

}