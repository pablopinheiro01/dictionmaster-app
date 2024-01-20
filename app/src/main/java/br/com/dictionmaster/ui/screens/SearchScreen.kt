package br.com.dictionmaster.ui.screens

import LanguageSelectedComponent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.dictionmaster.ui.components.DictionMasterButtonComponent
import br.com.dictionmaster.ui.theme.DictionMasterTheme
import br.com.dictionmaster.ui.uistates.SearchUiState
import br.com.dictionmaster.ui.viewmodels.SearchViewModel
import br.com.dictionmaster.ui.theme.textBlue as TextColor


@Composable
fun SearchScreen(
    state: SearchUiState,
    viewModel: SearchViewModel,
    navController: NavController
) {
    SearchScreen(
        state = state,
        onValueChangeSearchWord = { state.onValueChangedSearchWord(it) },
        onClickSearchButton = { state.onSearchButtonClick() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    state: SearchUiState,
    onValueChangeSearchWord: (value: String) -> Unit = {},
    onClickSearchButton: () -> Unit = {}
) {
    var showPlaceHolder by remember { mutableStateOf(true) }

    Scaffold { paddingValues ->
        Column(
            modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = modifier
                    .padding(paddingValues = paddingValues)
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(80.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier.padding(top = 30.dp)
                ) {
                    LanguageSelectedComponent()
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    OutlinedTextField(
                        modifier = modifier
                            .heightIn(38.dp)
                            .widthIn(174.dp),
                        value = state.word,
                        onValueChange = { valueString ->
                            onValueChangeSearchWord(valueString)
                            if (state.word.isNotEmpty()) showPlaceHolder = false
                        },
                        textStyle = TextStyle(
                            color = TextColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp,
                            textAlign = TextAlign.Center
                        ),
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color.Transparent,
                            focusedBorderColor = Color.Transparent,
                            disabledBorderColor = Color.Transparent,
                            textColor = TextColor,
                            unfocusedBorderColor = Color.Transparent,
                        ),
                        placeholder = {
                            if (showPlaceHolder) {
                                Text(
                                    "Type a word...",
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.ExtraLight,
                                    color = TextColor
                                )
                            }
                        },
                    )
                }

                Row{
                    DictionMasterButtonComponent(
                        onClickButton = { onClickSearchButton() },
                        textButton = "SEARCH"
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun SearchScreenPreview() {
    DictionMasterTheme {
        SearchScreen(state = SearchUiState())
    }
}