package br.com.dictionmaster.ui.uistates

import br.com.dictionmaster.model.WordDetail


sealed class ResultScreenUiState{
    object Loading : ResultScreenUiState()
    object Error: ResultScreenUiState()

    object NavigateToBuy: ResultScreenUiState()

    data class SuccessResultScreenUiState(
        val listWords: List<WordDetail?> = emptyList(),
        val loadingData: Boolean = false,
        val isShowError: Boolean = false
    ): ResultScreenUiState()

}