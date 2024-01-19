package br.com.dictionmaster.network.services

import androidx.compose.runtime.Composable
import br.com.dictionmaster.model.WordDetail
import br.com.dictionmaster.network.data.WordDetailResponse
import retrofit2.Response
import retrofit2.http.GET

interface DictionMasterService {

    companion object{
        const val URL = ""
    }

    @GET(URL)
    suspend fun searchWord(): Response<WordDetailResponse>

}