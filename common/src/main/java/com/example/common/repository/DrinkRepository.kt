package com.example.common.repository

import com.example.common.model.DrinkList
import com.example.common.util.NetworkError
import com.example.common.util.Result

interface DrinkRepository {
    suspend fun searchDrinksName(searchString: String): Result<DrinkList, NetworkError>
    suspend fun searchDrinksLetter(searchLetter: String): Result<DrinkList, NetworkError>
}