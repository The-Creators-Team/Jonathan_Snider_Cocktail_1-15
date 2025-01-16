package com.example.common.model

import kotlinx.serialization.Serializable

@Serializable
data class Drink(
    val idDrink: Int = 0,
    val strDrink: String = "empty",
    val strDrinkThumb: String = "Empty Thumb"
)
