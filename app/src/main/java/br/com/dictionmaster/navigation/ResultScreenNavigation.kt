package br.com.dictionmaster.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.dictionmaster.ui.screens.ResultScreen
import br.com.dictionmaster.ui.viewmodels.ResultScreenViewModel

const val resultScreenRoute = "resultScreen"
const val wordArgument = "wordArgument"
fun NavGraphBuilder.resultScreen(
    navController: NavController
) {
    composable(route = "${resultScreenRoute}/{$wordArgument}") {

        val viewModel = hiltViewModel<ResultScreenViewModel>()
        val state by viewModel.uiState.collectAsState()

        ResultScreen(
            state = state,
            navController = navController
        )

    }
}

fun NavController.navigateToResultScreen(word:String) {
    navigate(route = "${resultScreenRoute}/$word")
}