package com.xiaoyuanlv.pokedex.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.xiaoyuanlv.pokedex.data.model.Pokemon
import com.xiaoyuanlv.pokedex.databinding.ItemPokemonBinding

class PokemonAdapter :
    RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {
    private val items = mutableListOf<Pokemon>()
    inner class PokemonViewHolder(
        val binding: ItemPokemonBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = ItemPokemonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = items[position]

        holder.binding.txtName.text = pokemon.name.capitalize()

        val id = pokemon.url
            .trimEnd('/')
            .split("/")
            .last()

        val imageUrl =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"

        holder.binding.imagePokemon.load(imageUrl)
    }

    override fun getItemCount(): Int = items.size

    fun submitList(list: List<Pokemon>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
}
