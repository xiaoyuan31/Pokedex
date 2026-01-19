package com.xiaoyuanlv.pokedex.ui.pokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.xiaoyuanlv.pokedex.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val repository: PokemonRepository
)  : ViewModel() {

    val pokemonPaging = repository.getPokemonPaging().cachedIn(
        viewModelScope
    )

}