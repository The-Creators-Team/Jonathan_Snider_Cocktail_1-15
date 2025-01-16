package com.example.login

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cocktail.CocktailNavigation
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.serialization.Serializable

@Composable
fun LoginNavigation() {
    val loginAuth by remember { mutableStateOf(Firebase.auth) }
    val loginNavController = rememberNavController()
    NavHost(
        navController = loginNavController,
        startDestination = LoginScreenRoute,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.secondary)
    ){
        composable<LoginScreenRoute>{
            LoginScreen(
                auth = loginAuth,
                navigateToRegister = { loginNavController.navigate(RegisterScreenRoute) },
                navigateToCocktailNavigation = { loginNavController.navigate(CocktailNavigationRoute)}
            )
        }
        composable<RegisterScreenRoute> {
            RegisterScreen(
                auth =loginAuth,
                navigateToLogin = { loginNavController.navigate(LoginScreenRoute) }
            )
        }
        composable<CocktailNavigationRoute> {
            CocktailNavigation()
        }
    }
}

@Serializable
object LoginScreenRoute
@Serializable
object RegisterScreenRoute
@Serializable
object CocktailNavigationRoute
