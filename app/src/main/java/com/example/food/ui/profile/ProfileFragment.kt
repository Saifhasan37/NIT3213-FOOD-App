package com.example.food.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.food.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Setup back button
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
        
        // Set up profile data
        binding.userNameText.text = "MD Mahmudul Hasan"
        binding.userEmailText.text = "mahmudul@example.com"
        binding.userRoleText.text = "Food Enthusiast"
        binding.joinDateText.text = "Joined: January 2024"
        binding.totalDishesText.text = "7 dishes explored"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
