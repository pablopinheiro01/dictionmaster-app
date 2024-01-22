package br.com.dictionmaster.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dictionmaster.repositories.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PurchaseScreenViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {

    fun cleanData(){
        viewModelScope.launch(Dispatchers.IO){
            repository.clean()
        }
    }

}