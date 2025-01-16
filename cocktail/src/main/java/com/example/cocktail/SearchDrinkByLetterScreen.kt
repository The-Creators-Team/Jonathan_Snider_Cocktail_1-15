package com.example.cocktail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.common.model.DrinkList
import com.example.common.networking.DrinkClient
import com.example.common.util.onError
import com.example.common.util.onSuccess
import com.example.common.viewmodel.DrinkViewModel
import kotlinx.coroutines.launch

@Composable
fun SearchDrinkByLetterScreen(
    drinkClient: DrinkClient,
    //navigateToSearchDrinkByLetterScreen: () -> Unit
    drinkViewModel: DrinkViewModel
    //drinkViewModel: DrinkViewModel = viewModel()
) {
    val scope = rememberCoroutineScope()

    var drinkList by remember { mutableStateOf(DrinkList(emptyList())) }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {

            Surface {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(('A'..'Z').toList()) { letter ->
                        Text(
                            text = letter.toString(),
                            modifier = Modifier
                                .clickable {
                                    scope.launch {
                                        println("DOING API CALL")
                                        drinkClient.searchDrinksLetter(letter.toString())
                                            .onSuccess { example ->
                                                println("PRINTING SUCCESS")
                                                drinkList = example
                                                println(example)
                                            }
                                            .onError { example ->
                                                println("PRINTING ERROR")
                                                println(example)
                                            }
                                    }
                                }
                                .padding(8.dp),
                        )
                    }
                }
            }

            LazyColumn(
                modifier = Modifier
            ) {
                items(drinkList.drinks?.size ?: 0) { drink ->
                    DrinkCard(
                        drink = drinkList.drinks!![drink]
                    )
                }
            }
        }


        if (drinkList.drinks.isNullOrEmpty()) {
            Text(text = "waiting for drinks")
        }


        Column {
    /*        Button(
                onClick = {
                    scope.launch {
                        println("DOING API CALL")
                        drinkClient.searchDrinksName(drinkSearchText)
                            .onSuccess { example ->
                                println("PRINTING SUCCESS")
                                drinkList = example
                                println(example)
                            }
                            .onError { example ->
                                println("PRINTING ERROR")
                                println(example)
                            }
                    }
                }
            )*/
         /*   Button(
                onClick = navigateToSearchDrinkByLetterScreen
            ) {
                Text("Go To Next Screen")
            }*/
        }
    }
}