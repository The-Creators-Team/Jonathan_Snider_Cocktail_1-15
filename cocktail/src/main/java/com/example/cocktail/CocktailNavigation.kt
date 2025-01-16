package com.example.cocktail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.common.networking.DrinkClient
import com.example.common.networking.createHttpClient
import com.example.common.repository.DrinkRepositoryImpl
import com.example.common.viewmodel.DrinkViewModel
import io.ktor.client.engine.okhttp.OkHttp
import kotlinx.serialization.Serializable

@Composable
fun CocktailNavigation() {
    val drinkNavController = rememberNavController()

    val drinkClient = remember {
        DrinkClient(createHttpClient(OkHttp.create()))
    }
    val drinkViewModel= DrinkViewModel(
        drinkRepositoryImpl = DrinkRepositoryImpl(
            drinkClient = DrinkClient(createHttpClient(OkHttp.create()))
        )
    )
    NavHost(
        navController = drinkNavController,
        startDestination = SearchDrinkByNameScreenRoute
    ) {
        composable<SearchDrinkByNameScreenRoute> {
            SearchDrinkByNameScreen(
                //drinkClient = drinkClient,
                navigateToSearchDrinkByLetterScreen = {
                    drinkNavController.navigate(
                        SearchDrinkByLetterScreenRoute
                    )
                },
                drinkViewModel = drinkViewModel
            )
        }
        composable<SearchDrinkByLetterScreenRoute> {
            SearchDrinkByLetterScreen(
                drinkClient=drinkClient

            )
        }
    }
}

@Serializable
object SearchDrinkByNameScreenRoute

@Serializable
object SearchDrinkByLetterScreenRoute
