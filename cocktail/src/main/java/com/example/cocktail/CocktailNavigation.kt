package com.example.cocktail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.common.networking.DrinkClient
import com.example.common.networking.createHttpClient
import io.ktor.client.engine.okhttp.OkHttp
import kotlinx.serialization.Serializable

@Composable
fun CocktailNavigation() {
    val drinkNavController = rememberNavController()

    val drinkClient = remember {
        DrinkClient(createHttpClient(OkHttp.create()))
    }
    NavHost(
        navController = drinkNavController,
        startDestination = SearchDrinkByNameScreenRoute
    ) {
        composable<SearchDrinkByNameScreenRoute> {
            SearchDrinkByNameScreen(
                drinkClient = drinkClient,
                navigateToSearchDrinkByLetterScreen = {
                    drinkNavController.navigate(
                        SearchDrinkByLetterScreenRoute
                    )
                }
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
