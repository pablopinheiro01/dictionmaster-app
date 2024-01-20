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
        startDestination = searchScreenRoute
    ){
        splashScreen()
        searchScreen(navHostController)
    }
}

