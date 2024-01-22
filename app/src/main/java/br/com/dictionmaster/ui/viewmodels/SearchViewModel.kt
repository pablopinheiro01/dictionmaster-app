package br.com.dictionmaster.ui.viewmodels

import androidx.lifecycle.ViewModel
import br.com.dictionmaster.ui.uistates.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {

    private val _wordMutableStateFlow = MutableStateFlow("")

    val wordMutableStateFlow: StateFlow<String>
        get() = _wordMutableStateFlow

    private val _uiState = MutableStateFlow<SearchUiState>(
        SearchUiState.SuccessSearchUiState()
    )

    val uiState: StateFlow<SearchUiState>
        get() = _uiState.asStateFlow()

    fun onValueChangedSearchWord(word: String) {
        _wordMutableStateFlow.value = word
        _uiState.value = _uiState.value.let {
            when (it) {
                is SearchUiState.SuccessSearchUiState -> {
                    SearchUiState.SuccessSearchUiState(
                        word = word,
                        onValueChangedSearchWord = { onValueChangedSearchWord(it) }
                    )
                }
                else -> {}
            }
        } as SearchUiState
    }

    fun changed() {
        _uiState.update { SearchUiState.SuccessSearchUiState() }
    }

}