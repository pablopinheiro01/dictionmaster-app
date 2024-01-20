package br.com.dictionmaster.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.dictionmaster.ui.screens.SplashScreen

const val splashScreenRoute = "splashScreen"
fun NavGraphBuilder.splashScreen(){
    composable(splashScreenRoute){
        SplashScreen()
    }
}

fun NavController.navigateToSplashScreen(){
    navigate(route = splashScreenRoute)
}