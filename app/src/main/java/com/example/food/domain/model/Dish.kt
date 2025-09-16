package com.example.food.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dish(
    val dishName: String,
    val origin: String,
    val mainIngredient: String,
    val mealType: String,
    val description: String
) : Parcelable

