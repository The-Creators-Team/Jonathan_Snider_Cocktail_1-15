package com.example.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.model.DrinkList
import com.example.common.repository.DrinkRepositoryImpl
import com.example.common.util.onError
import com.example.common.util.onSuccess
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DrinkViewModel(
    private val drinkRepositoryImpl: DrinkRepositoryImpl
) : ViewModel() {
    // StateFlow for the list of drinks
    private val _drinks = MutableStateFlow<DrinkList>(DrinkList(emptyList()))
    val drinks: StateFlow<DrinkList> = _drinks.asStateFlow()

    // SharedFlow for API call result
  /*  private val _apiResult = MutableSharedFlow<ApiResult>()
    val apiResult: SharedFlow<ApiResult> = _apiResult.asSharedFlow()*/


    fun searchDrinksByName(searchString: String) {
        viewModelScope.launch {
            println("DOING API CALL")
            drinkRepositoryImpl.searchDrinksName(searchString)
                .onSuccess { example ->
                    println("PRINTING SUCCESS")
                    _drinks.value = example
                    println(example)
                }
                .onError { example ->
                    println("PRINTING ERROR")
                    println(example)
                }
        }
    }

    fun searchDrinksByLetter(searchLetter: String){
        viewModelScope.launch {
            println("DOING API CALL")
            drinkRepositoryImpl.searchDrinksLetter(searchLetter)
                .onSuccess { example ->
                    println("PRINTING SUCCESS")
                    _drinks.value = example
                    println(example)
                }
                .onError { example ->
                    println("PRINTING ERROR")
                    println(example)
                }
        }
    }

}