package com.example.food.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.food.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Setup back button
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
        
        // Set up settings data
        binding.notificationsSwitch.isChecked = true
        binding.darkModeSwitch.isChecked = false
        binding.autoSyncSwitch.isChecked = true
        
        binding.versionText.text = "Version 1.0.0"
        binding.buildText.text = "Build 2024.01.15"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
