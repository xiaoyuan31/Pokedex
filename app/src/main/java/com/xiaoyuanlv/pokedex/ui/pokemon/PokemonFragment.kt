package com.xiaoyuanlv.pokedex.ui.pokemon

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.xiaoyuanlv.pokedex.databinding.FragmentPokemonBinding
import com.xiaoyuanlv.pokedex.ui.adapter.PokemonPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonFragment : Fragment() {

    private var _binding: FragmentPokemonBinding? = null
    private val binding get() = _binding!!
    private val adapter = PokemonPagingAdapter()

    private val viewModel: PokemonViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.recyclerView.layoutManager = GridLayoutManager(
            requireContext(),
            2 // number of columns
        )

        binding.recyclerView.setHasFixedSize(true)

      //  binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.pokemonPaging.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}