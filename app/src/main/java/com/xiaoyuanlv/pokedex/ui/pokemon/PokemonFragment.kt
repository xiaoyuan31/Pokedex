package com.xiaoyuanlv.pokedex.ui.pokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.xiaoyuanlv.pokedex.databinding.FragmentPokemonBinding
import com.xiaoyuanlv.pokedex.ui.adapter.PokemonLoadStateAdapter
import com.xiaoyuanlv.pokedex.ui.adapter.PokemonPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonFragment : Fragment() {

    private var _binding: FragmentPokemonBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: PokemonPagingAdapter
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

        setupRecyclerView()
        collectPagingData()
       // observeLoadState()
    }

    private fun setupRecyclerView() {
        adapter = PokemonPagingAdapter()

        // Grid layout with 2 columns
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.setHasFixedSize(true)

        // Wrap with header/footer LoadStateAdapter (create only once)
        binding.recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PokemonLoadStateAdapter { adapter.retry() },
            footer = PokemonLoadStateAdapter { adapter.retry() }
        )
        binding.recyclerView.apply {
            isVerticalScrollBarEnabled = true
            scrollBarFadeDuration = 0
            scrollBarDefaultDelayBeforeFade = 0
        }
    }

    private fun collectPagingData() {
        lifecycleScope.launch {
            viewModel.pokemonPaging.collectLatest { pagingData ->
                adapter.submitData(pagingData) // Paging handles scroll preservation automatically
            }
        }
    }

//    private fun observeLoadState() {
//        adapter.addLoadStateListener { loadState ->
//            // Show central loading spinner while refreshing
//            binding.progressBar.isVisible = loadState.refresh is LoadState.Loading
//
//            // Show retry button on refresh error
//            binding.tvError.isVisible = loadState.refresh is LoadState.Error
//            binding.btnRetry.isVisible = loadState.refresh is LoadState.Error
//            binding.btnRetry.setOnClickListener { adapter.retry() }
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
