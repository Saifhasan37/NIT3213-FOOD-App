package com.example.food.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food.data.repo.FoodRepository
import com.example.food.domain.model.Dish
import com.example.food.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: FoodRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Dish>>>(UiState.Idle)
    val uiState: StateFlow<UiState<List<Dish>>> = _uiState.asStateFlow()

    init {
        loadDishes()
    }

    private fun loadDishes() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val keypass = repository.observeKeypass().first()
                if (keypass.isNotEmpty()) {
                    repository.fetchDishes(keypass)
                        .onSuccess { dishes ->
                            _uiState.value = UiState.Success(dishes)
                        }
                        .onFailure { exception ->
                            _uiState.value = UiState.Error(exception.message ?: "Failed to load dishes")
                        }
                } else {
                    _uiState.value = UiState.Error("No keypass found")
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Error loading dishes: ${e.message}")
            }
        }
    }

    fun refresh() {
        loadDishes()
    }
}

