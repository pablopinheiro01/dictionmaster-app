package br.com.dictionmaster.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.dictionmaster.ui.screens.PurchaseScreen
import br.com.dictionmaster.ui.viewmodels.PurchaseScreenViewModel

const val purchaseScreenRoute = "purchaseScreen"
fun NavGraphBuilder.purchaseScreen(
    navController: NavController
) {
    composable(purchaseScreenRoute) {

        val viewModel = hiltViewModel<PurchaseScreenViewModel>()
//        val state by viewModel.uiState.collectAsState()
//        val context = LocalContext.current

        PurchaseScreen(
            viewModel = viewModel
        )

    }
}

fun NavController.navigateToPurchaseScreen() {
    navigate(route = purchaseScreenRoute)
}