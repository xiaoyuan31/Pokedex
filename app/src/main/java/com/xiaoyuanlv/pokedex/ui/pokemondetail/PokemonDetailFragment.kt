package com.xiaoyuanlv.pokedex.ui.pokemondetail

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import android.view.animation.AnimationUtils
import com.xiaoyuanlv.pokedex.R
import com.xiaoyuanlv.pokedex.data.local.entity.PokemonEntity
import com.xiaoyuanlv.pokedex.databinding.FragmentPokemonBinding
import com.xiaoyuanlv.pokedex.databinding.FragmentPokemonDetailBinding
import com.xiaoyuanlv.pokedex.utils.TypeColor
import com.xiaoyuanlv.pokedex.utils.setProgressSmooth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailFragment : Fragment() {

    private val args: PokemonDetailFragmentArgs by navArgs()
    private val viewModel: PokemonDetailViewModel by viewModels()

    private lateinit var binding : FragmentPokemonDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonDetailBinding.inflate(inflater)

        viewModel.pokemonDetail.observe(viewLifecycleOwner) { pokemon ->
            if(pokemon != null) {
                bindPokemon(pokemon)
            }
        }

        viewModel.loadPokemon(args.pokemonId)

        return binding.root
    }

    private fun bindPokemon(pokemon: PokemonEntity) {
        // Header and main image
       // binding.imageHeader.load(pokemon.imageUrl)
       // binding.imagePokemon.load(pokemon.imageUrl)

        val imageResId = resources.getIdentifier(
            "pokemon"+pokemon.id, // "pokemon1"
            "drawable",
            requireContext().packageName
        )
        binding.imagePokemon.setImageResource(imageResId)
        binding.imageHeader.setImageResource(imageResId)

        // Name and ID
        binding.textName.text = pokemon.name.replaceFirstChar { it.uppercase() }
        binding.textId.text = "#${pokemon.id.toString().padStart(3, '0')}"

        // Types
        binding.typeContainer.removeAllViews()
        pokemon.types.forEach { type ->
            val badge = TextView(requireContext()).apply {
                text = type.replaceFirstChar { it.uppercase() }
                setTextColor(Color.WHITE)
                setPadding(16, 8, 16, 8)
                textSize = 12f
                background = GradientDrawable().apply {
                    cornerRadius = 50f
                    setColor(TypeColor.getColor(type))
                }
            }
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 8, 0) }
            binding.typeContainer.addView(badge, params)
        }

        // Stats
        binding.progressHp.setProgressSmooth(pokemon.hp)
        binding.progressAttack.setProgressSmooth(pokemon.attack)
        binding.progressDefense.setProgressSmooth(pokemon.defense)

        val anim = AnimationUtils.loadAnimation(
            binding.progressHp.context,
            R.anim.fade_scale
        )

        binding.progressHp.visibility = View.VISIBLE
        binding.progressHp.clearAnimation()
        binding.progressHp.startAnimation(anim)


        val anim2 = AnimationUtils.loadAnimation(
            binding.progressAttack.context,
            R.anim.fade_scale
        )

        binding.progressAttack.visibility = View.VISIBLE
        binding.progressAttack.clearAnimation()
        binding.progressAttack.startAnimation(anim2)


        val anim3 = AnimationUtils.loadAnimation(
            binding.progressDefense.context,
            R.anim.fade_scale
        )

        binding.progressDefense.visibility = View.VISIBLE
        binding.progressDefense.clearAnimation()
        binding.progressDefense.startAnimation(anim3)

        binding.tvSpeedValue.text = pokemon.speed.toString()
        binding.pbSpeed.setProgress(pokemon.speed)

        binding.tvSpAttackValue.text = pokemon.specialAttack.toString()
        binding.pbSpAttack.setProgress(pokemon.specialAttack)

        binding.tvSpDefenseValue.text = pokemon.specialDefense.toString()
        binding.pbSpDefense.setProgress(pokemon.specialDefense)



    }

}

