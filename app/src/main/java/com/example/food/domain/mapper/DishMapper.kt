package com.example.food.domain.mapper

import com.example.food.data.remote.dto.DishDto
import com.example.food.domain.model.Dish

fun DishDto.toDomain(): Dish = Dish(
    dishName = dishName,
    origin = origin,
    mainIngredient = mainIngredient,
    mealType = mealType,
    description = description
)

