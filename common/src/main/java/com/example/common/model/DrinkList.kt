package com.example.common.model

import kotlinx.serialization.Serializable

@Serializable
data class DrinkList(
    val drinks: List<Drink>
)
