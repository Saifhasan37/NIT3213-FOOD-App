package com.example.food.data.repo

import com.example.food.data.local.KeypassStore
import com.example.food.data.remote.ApiService
import com.example.food.data.remote.dto.LoginRequest
import com.example.food.domain.mapper.toDomain
import com.example.food.domain.model.Dish
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodRepository @Inject constructor(
    private val apiService: ApiService,
    private val keypassStore: KeypassStore
) {

    suspend fun login(username: String, password: String): Result<String> {
        return try {
            val request = LoginRequest(username = username, password = password)
            val response = apiService.login(request)
            keypassStore.save(response.keypass)
            Result.success(response.keypass)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun fetchDishes(keypass: String): Result<List<Dish>> {
        return try {
            val response = apiService.getEntities(keypass)
            val dishes = response.entities.map { it.toDomain() }
            Result.success(dishes)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun observeKeypass(): Flow<String> = keypassStore.keypassFlow
}
