package com.example.food.domain.mapper

import com.example.food.data.remote.dto.DishDto
import com.example.food.domain.model.Dish
import org.junit.Assert.assertEquals
import org.junit.Test

class DishMapperTest {

    @Test
    fun `toDomain should map DishDto to Dish correctly`() {
        // Given
        val dishDto = DishDto(
            dishName = "Pasta Carbonara",
            origin = "Italy",
            mainIngredient = "Pasta",
            mealType = "Dinner",
            description = "A classic Italian pasta dish"
        )

        // When
        val result = dishDto.toDomain()

        // Then
        assertEquals("Pasta Carbonara", result.dishName)
        assertEquals("Italy", result.origin)
        assertEquals("Pasta", result.mainIngredient)
        assertEquals("Dinner", result.mealType)
        assertEquals("A classic Italian pasta dish", result.description)
    }
}

