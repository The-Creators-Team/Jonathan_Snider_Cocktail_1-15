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
    ){
        composable<SearchDrinkByNameScreenRoute> {
            SearchDrinkByNameScreen(
                drinkClient =drinkClient
            )
        }
    }
}

@Serializable
object SearchDrinkByNameScreenRoute


/*
@Composable
fun DrinkCard(
    drink: Drink
) {
    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = drink.imageResId),
                contentDescription = "Drink Image",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))

            // Drink Name
            Text(
                text = drink.name,
                modifier = Modifier.weight(1f)
            )
        }
    }
}*/
