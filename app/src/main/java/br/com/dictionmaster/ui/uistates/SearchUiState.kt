package br.com.dictionmaster.ui.uistates


sealed class SearchUiState{

    object Loading: SearchUiState()

    data class SuccessSearchUiState(
        val word: String = "",
        val onValueChangedSearchWord: (value: String) -> Unit = {},
    ): SearchUiState()

}