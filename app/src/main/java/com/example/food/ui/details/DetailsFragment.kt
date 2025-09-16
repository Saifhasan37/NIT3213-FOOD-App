package com.example.food.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.food.databinding.FragmentDetailsBinding
import com.example.food.domain.model.Dish
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup back button
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        val dish = arguments?.getParcelable<Dish>("dish")
        if (dish != null) {
            binding.dishNameTextView.text = dish.dishName
            binding.originTextView.text = dish.origin
            binding.mainIngredientTextView.text = dish.mainIngredient
            binding.mealTypeTextView.text = dish.mealType
            binding.descriptionTextView.text = dish.description
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
