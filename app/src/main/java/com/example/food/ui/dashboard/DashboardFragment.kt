package com.example.food.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.food.R
import com.example.food.databinding.FragmentDashboardBinding
import com.example.food.util.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DashboardViewModel by viewModels()
    private lateinit var dishAdapter: DishAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSwipeRefresh()
        setupNavigationButtons()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        dishAdapter = DishAdapter { dish ->
            val bundle = Bundle().apply {
                putParcelable("dish", dish)
            }
            findNavController().navigate(R.id.action_dashboard_to_details, bundle)
        }
        
        binding.dishRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dishAdapter
        }
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    private fun setupNavigationButtons() {
        binding.settingsButton.setOnClickListener {
            findNavController().navigate(R.id.action_dashboard_to_settings)
        }
        
        binding.profileButton.setOnClickListener {
            findNavController().navigate(R.id.action_dashboard_to_profile)
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state) {
                    is UiState.Idle -> {
                        binding.progressBar.visibility = View.GONE
                        binding.errorTextView.visibility = View.GONE
                        binding.swipeRefreshLayout.isRefreshing = false
                    }
                    is UiState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.errorTextView.visibility = View.GONE
                        binding.swipeRefreshLayout.isRefreshing = false
                    }
                    is UiState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.errorTextView.visibility = View.GONE
                        binding.swipeRefreshLayout.isRefreshing = false
                        dishAdapter.submitList(state.data)
                    }
                    is UiState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.errorTextView.visibility = View.VISIBLE
                        binding.errorTextView.text = state.message
                        binding.swipeRefreshLayout.isRefreshing = false
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

