package com.example.food.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food.data.repo.FoodRepository
import com.example.food.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: FoodRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<Unit>>(UiState.Idle)
    val uiState: StateFlow<UiState<Unit>> = _uiState.asStateFlow()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.login(username, password)
                .onSuccess { keypass ->
                    android.util.Log.d("LoginViewModel", "Login successful with keypass: $keypass")
                    _uiState.value = UiState.Success(Unit)
                }
                .onFailure { exception ->
                    android.util.Log.e("LoginViewModel", "Login failed: ${exception.message}")
                    _uiState.value = UiState.Error(exception.message ?: "Login failed")
                }
        }
    }
}
