package com.example.food.ui.login

import com.example.food.data.repo.FoodRepository
import com.example.food.util.UiState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    private val repository = mockk<FoodRepository>()
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = LoginViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `login should emit Success when repository returns success`() = runTest {
        // Given
        val username = "Saif"
        val password = "8089637"
        val expectedKeypass = "food"
        
        coEvery { repository.login(username, password) } returns Result.success(expectedKeypass)

        // When
        viewModel.login(username, password)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val uiState = viewModel.uiState.value
        assertTrue(uiState is UiState.Success)
    }

    @Test
    fun `login should emit Error when repository returns failure`() = runTest {
        // Given
        val username = "Saif"
        val password = "8089637"
        val errorMessage = "Login failed"
        
        coEvery { repository.login(username, password) } returns Result.failure(Exception(errorMessage))

        // When
        viewModel.login(username, password)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val uiState = viewModel.uiState.value
        assertTrue(uiState is UiState.Error)
        assertEquals(errorMessage, (uiState as UiState.Error).message)
    }
}

