package br.com.dictionmaster.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dictionmaster.ui.uistates.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
): ViewModel(){
    private val _uiState = MutableStateFlow(SearchUiState())

    val uiState: StateFlow<SearchUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {

        }
    }

    private fun search(word: String){

    }



}