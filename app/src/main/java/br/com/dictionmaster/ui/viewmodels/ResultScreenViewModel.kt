package br.com.dictionmaster.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dictionmaster.exceptions.AllAttemptsMadeBuyAppException
import br.com.dictionmaster.exceptions.DataNotFoundException
import br.com.dictionmaster.navigation.wordArgument
import br.com.dictionmaster.repositories.SearchRepository
import br.com.dictionmaster.ui.uistates.ResultScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultScreenViewModel @Inject constructor(
    private val repository: SearchRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<ResultScreenUiState>(
        ResultScreenUiState.Loading
    )

    val uiState: StateFlow<ResultScreenUiState>
        get() = _uiState.asStateFlow()

    private val wordStateHandler = savedStateHandle.get<String>(wordArgument)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { ResultScreenUiState.Loading }
            search()
        }
    }

    private fun search() {
        viewModelScope.launch(Dispatchers.IO){
            wordStateHandler?.let { word ->
                try {
                    repository.search(word)?.let { listWord ->
                        if(listWord.isNullOrEmpty()){
                            _uiState.update { ResultScreenUiState.Error }
                        }else{
                            _uiState.update {
                                ResultScreenUiState.SuccessResultScreenUiState(
                                    listWords = listWord,
                                    loadingData = false
                                )
                            }
                        }
                    }
                } catch (e: DataNotFoundException) {
                    _uiState.update {
                        ResultScreenUiState.Error
                    }
                }catch (e: AllAttemptsMadeBuyAppException){
                    _uiState.update {
                        ResultScreenUiState.NavigateToBuy
                    }
                }
            }
        }
    }
}