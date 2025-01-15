package com.example.login

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@Composable
fun LoginNavigation() {
    //val loginAuth by remember { mutableStateOf(Firebase.auth) }
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = LoginScreenRoute
    ){
        composable<LoginScreenRoute>{
            LoginScreen()
        }
        composable<RegisterScreenRoute> {
            RegisterScreen()
        }
    }
}

@Serializable
object LoginScreenRoute
@Serializable
object RegisterScreenRoute
