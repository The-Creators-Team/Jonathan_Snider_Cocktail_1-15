package com.example.cocktail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.common.model.Drink
import com.example.common.model.DrinkList
import com.example.common.networking.DrinkClient
import com.example.common.util.onError
import com.example.common.util.onSuccess
import kotlinx.coroutines.launch


@Composable
fun SearchDrinkByNameScreen(
    drinkClient: DrinkClient,
    navigateToSearchDrinkByLetterScreen: () -> Unit
) {
    val scope = rememberCoroutineScope()

    var drinkList by remember { mutableStateOf(DrinkList(emptyList())) }
    var drinkSearchText by remember { mutableStateOf("") }
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
                OutlinedTextField(
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedTextColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedLabelColor = MaterialTheme.colorScheme.primary,
                        unfocusedLeadingIconColor = MaterialTheme.colorScheme.primary
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null
                        )
                    },
                    value = drinkSearchText,
                    onValueChange = { drinkSearchText = it },
                    label = { Text(stringResource(R.string.drink_search_field)) },
                    modifier = Modifier.padding(10.dp)
                )
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
            Button(
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
            ) {
                Text("Make API call")
            }
            Button(
                onClick = navigateToSearchDrinkByLetterScreen
            ) {
                Text("Go To Next Screen")
            }
        }
    }
}

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

            NetworkImageDisplay(imageUrl = drink.strDrinkThumb)
            Spacer(modifier = Modifier.width(16.dp))

            // Drink Name
            Text(
                text = drink.strDrink,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun NetworkImageDisplay(imageUrl: String) {
    AsyncImage(
        model = imageUrl,
        contentDescription = "Drink Image",
        placeholder = painterResource(id = R.drawable.ic_gear), // Your placeholder resource
        error = painterResource(id = R.drawable.ic_cancel),
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
    )
}