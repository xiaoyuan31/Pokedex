package com.xiaoyuanlv.pokedex.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.xiaoyuanlv.pokedex.R
import com.xiaoyuanlv.pokedex.data.local.entity.PokemonEntity
import com.xiaoyuanlv.pokedex.utils.PokemonUtils

class PokemonPagingAdapter :
    PagingDataAdapter<PokemonEntity, PokemonPagingAdapter.VH>(DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(pokemon: PokemonEntity) {
            val id = PokemonUtils.getPokemonId(pokemon.url)
            itemView.findViewById<TextView>(R.id.txtName).text = pokemon.name.replaceFirstChar { it.uppercase() }
            val imageUrl = PokemonUtils.getImageUrl(id)
            itemView.findViewById<ImageView>(R.id.imagePokemon).load(imageUrl) {
                crossfade(true)
            }
        }
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<PokemonEntity>() {
            override fun areItemsTheSame(a: PokemonEntity, b: PokemonEntity) =
                a.name == b.name

            override fun areContentsTheSame(a: PokemonEntity, b: PokemonEntity) =
                a == b
        }
    }
}