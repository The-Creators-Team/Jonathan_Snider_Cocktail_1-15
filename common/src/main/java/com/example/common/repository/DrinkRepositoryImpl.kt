package com.example.common.repository

import com.example.common.model.DrinkList
import com.example.common.networking.DrinkClient
import com.example.common.util.NetworkError
import com.example.common.util.Result

class DrinkRepositoryImpl(private val drinkClient: DrinkClient): DrinkRepository {
    override suspend fun searchDrinksName(searchString: String): Result<DrinkList, NetworkError> {
        return drinkClient.searchDrinksName(searchString)
    }



    override suspend fun searchDrinksLetter(searchLetter: String): Result<DrinkList, NetworkError> {
        return drinkClient.searchDrinksLetter(searchLetter)
    }
}