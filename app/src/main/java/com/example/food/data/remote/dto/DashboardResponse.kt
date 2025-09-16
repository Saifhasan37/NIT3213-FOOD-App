package com.example.food.data.remote.dto

data class DashboardResponse(
    val entities: List<DishDto>,
    val entityTotal: Int
)

