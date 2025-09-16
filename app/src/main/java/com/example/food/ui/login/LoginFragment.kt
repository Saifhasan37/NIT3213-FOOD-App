package com.example.food.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.food.R
import com.example.food.databinding.FragmentLoginBinding
import com.example.food.util.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        android.util.Log.d("LoginFragment", "onCreateView called")
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        android.util.Log.d("LoginFragment", "Binding created successfully")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        android.util.Log.d("LoginFragment", "onViewCreated called")

        binding.loginButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            viewModel.login(username, password)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state) {
                    is UiState.Idle -> {
                        binding.progressBar.visibility = View.GONE
                        binding.errorTextView.visibility = View.GONE
                        binding.loginButton.isEnabled = true
                    }
                    is UiState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.errorTextView.visibility = View.GONE
                        binding.loginButton.isEnabled = false
                    }
                    is UiState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.errorTextView.visibility = View.GONE
                        binding.loginButton.isEnabled = true
                        android.util.Log.d("LoginFragment", "Login successful, navigating to dashboard")
                        findNavController().navigate(R.id.action_login_to_dashboard)
                    }
                    is UiState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.errorTextView.visibility = View.VISIBLE
                        binding.errorTextView.text = state.message
                        binding.loginButton.isEnabled = true
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
