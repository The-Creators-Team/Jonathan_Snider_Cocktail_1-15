package com.example.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.model.DrinkList
import com.example.common.repository.DrinkRepositoryImpl
import com.example.common.util.onError
import com.example.common.util.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DrinkViewModel(
    private val drinkRepositoryImpl: DrinkRepositoryImpl
) : ViewModel() {

    private val _drinksByName = MutableStateFlow<DrinkList>(DrinkList(emptyList()))
    val drinksByName: StateFlow<DrinkList> = _drinksByName.asStateFlow()

    private val _drinksByLetter = MutableStateFlow<DrinkList>(DrinkList(emptyList()))
    val drinksByLetter: StateFlow<DrinkList> = _drinksByLetter.asStateFlow()


    fun searchDrinksByName(searchString: String) {
        viewModelScope.launch {
            println("DOING API CALL")
            drinkRepositoryImpl.searchDrinksName(searchString)
                .onSuccess { example ->
                    println("PRINTING SUCCESS")
                    _drinksByName.value = example
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
                    _drinksByLetter.value = example
                    println(example)
                }
                .onError { example ->
                    println("PRINTING ERROR")
                    println(example)
                }
        }
    }
}