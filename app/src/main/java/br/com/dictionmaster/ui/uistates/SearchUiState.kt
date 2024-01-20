package br.com.dictionmaster.ui.uistates

data class SearchUiState(
    val word: String = "",
    val showError: Boolean = false,
    val onSearchButtonClick: () -> Unit = {},
    val onValueChangedSearchWord: (value: String) -> Unit = {},
    val onNavigateToShowResults: Boolean = false,
    val onLoading: Boolean = false,
)