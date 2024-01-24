package br.com.dictionmaster.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.dictionmaster.ui.screens.SearchScreen
import br.com.dictionmaster.ui.viewmodels.SearchViewModel

const val searchScreenRoute = "searchScreen"
fun NavGraphBuilder.searchScreen(
    navController: NavController
) {
    composable(searchScreenRoute) {

        val viewModel = hiltViewModel<SearchViewModel>()
        val state by viewModel.uiState.collectAsState()

        SearchScreen(
            state = state,
            viewModel = viewModel,
            navController = navController
        )

    }
}

fun NavController.navigateToSearchScreen() {
    navigate(route = searchScreenRoute)
}