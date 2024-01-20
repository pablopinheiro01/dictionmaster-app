package br.com.dictionmaster.network.services

import androidx.compose.runtime.Composable
import br.com.dictionmaster.model.WordDetail
import br.com.dictionmaster.network.data.WordDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionMasterService {

    companion object{
        const val URL = "/api/v2/entries/en/{word}"
    }

    @GET(URL)
    suspend fun search(@Path("word") word: String): Response<List<WordDetailResponse>>

}