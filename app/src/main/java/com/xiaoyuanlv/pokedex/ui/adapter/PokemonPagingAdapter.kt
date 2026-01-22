package com.xiaoyuanlv.pokedex.ui.adapter

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.xiaoyuanlv.pokedex.R
import com.xiaoyuanlv.pokedex.data.local.entity.PokemonEntity
import com.xiaoyuanlv.pokedex.databinding.FragmentPokemonBinding
import com.xiaoyuanlv.pokedex.ui.pokemon.PokemonFragmentDirections
import com.xiaoyuanlv.pokedex.utils.PokemonUtils
import com.xiaoyuanlv.pokedex.utils.TypeColor

class PokemonPagingAdapter(
    private val onItemClick: (Int, VH) -> Unit
):
    PagingDataAdapter<PokemonEntity, PokemonPagingAdapter.VH>(DIFF) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon, parent, false)
        return VH(view, onItemClick)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class VH(view: View, private val onItemClick: (Int, VH) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bind(pokemon: PokemonEntity) {
            itemView.findViewById<TextView>(R.id.txtID).text = pokemon.id.toString()
            itemView.findViewById<TextView>(R.id.txtName).text = pokemon.name.replaceFirstChar { it.uppercase() }

            //itemView.findViewById<ImageView>(R.id.imagePokemon).load(pokemon.imageUrl)
            val imageResId = itemView.context.resources.getIdentifier(
                "pokemon"+pokemon.id, // "pokemon1"
                "drawable",
                itemView.context.packageName
            )
            itemView.findViewById<ImageView>(R.id.imagePokemon).setImageResource(imageResId)

            itemView.findViewById<LinearLayout>(R.id.typeContainer).removeAllViews()

            itemView.findViewById<RelativeLayout>(R.id.rlPokemon).setOnClickListener {
                onItemClick(pokemon.id, this)
            }

            pokemon.types.forEach { type ->
                val badge = TextView(itemView.context).apply {
                    text = type.capitalize()
                    setTextColor(Color.WHITE)
                    setPadding(16, 8, 16, 8)
                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
                    background = GradientDrawable().apply {
                        cornerRadius = 50f
                        setColor(TypeColor.getColor(type))
                    }
                }
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 0, 8, 0)
                }
                itemView.findViewById<LinearLayout>(R.id.typeContainer).addView(badge, params)
            }
        }
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<PokemonEntity>() {
            override fun areItemsTheSame(a: PokemonEntity, b: PokemonEntity) =
                a.id == b.id

            override fun areContentsTheSame(a: PokemonEntity, b: PokemonEntity) =
                a == b
        }
    }
}