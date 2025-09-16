package com.example.food.data.remote

import com.example.food.data.remote.dto.DashboardResponse
import com.example.food.data.remote.dto.LoginRequest
import com.example.food.data.remote.dto.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("sydney/auth")
    suspend fun login(@Body body: LoginRequest): LoginResponse

    @GET("dashboard/{keypass}")
    suspend fun getEntities(@Path("keypass") keypass: String): DashboardResponse
}
