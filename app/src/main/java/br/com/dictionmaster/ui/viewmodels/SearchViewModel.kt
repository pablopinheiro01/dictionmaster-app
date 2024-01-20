package br.com.dictionmaster.ui.viewmodels

import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dictionmaster.repositories.SearchRepository
import br.com.dictionmaster.ui.uistates.SearchUiState
import br.com.dictionmaster.util.firstCharToUpperCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())

    val uiState: StateFlow<SearchUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { currentState ->
                currentState.copy(
                    onValueChangedSearchWord = { word ->
                        _uiState.value = _uiState.value.copy(
                            word = word,
                            showError = false
                        )
                    },
                    onSearchButtonClick = {
                        search()
                    }
                )
            }
        }
    }

    fun search() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { currentState ->
                currentState.copy(
                    onLoading = true,
                )
            }
            val searchResult = repository.search(_uiState.value.word.firstCharToUpperCase())
            if (searchResult.isNullOrEmpty()) {
                _uiState.update { currentState ->
                    currentState.copy(
                        showError = true,
                        onLoading = false
                    )
                }
            } else {
                _uiState.update { currentState ->
                    currentState.copy(
                        showError = false,
                        onLoading = false,
                        onNavigateToShowResults = true
                    )
                }
            }

        }
    }


}