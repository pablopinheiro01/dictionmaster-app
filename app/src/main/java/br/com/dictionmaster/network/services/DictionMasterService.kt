package br.com.dictionmaster.network.services

import androidx.compose.runtime.Composable
import retrofit2.http.GET

interface DictionMasterService {

    companion object{
        const val URL = ""
    }

    @GET(URL)
    suspend fun searchWord()

}