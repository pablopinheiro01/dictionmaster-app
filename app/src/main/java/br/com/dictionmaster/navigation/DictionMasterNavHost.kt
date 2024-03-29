package br.com.dictionmaster.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost


@Composable
fun DictionMasterNavHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = splashScreenRoute
    ){
        splashScreen(navHostController)
        searchScreen(navHostController)
        resultScreen(navHostController)
        purchaseScreen()
    }
}