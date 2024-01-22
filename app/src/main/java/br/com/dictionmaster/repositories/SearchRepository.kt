package br.com.dictionmaster.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import br.com.dictionmaster.database.dao.WordsDao
import br.com.dictionmaster.database.entities.WordDetailEntity
import br.com.dictionmaster.exceptions.AllAttemptsMadeBuyAppException
import br.com.dictionmaster.exceptions.DataNotFoundException
import br.com.dictionmaster.model.WordDetail
import br.com.dictionmaster.model.toWordDetail
import br.com.dictionmaster.network.data.WordDetailResponse
import br.com.dictionmaster.network.services.DictionMasterService
import br.com.dictionmaster.preferences.PreferencesKey
import br.com.dictionmaster.util.convertFromJsonGenericList
import br.com.dictionmaster.util.convertToJsonGeneric
import kotlinx.coroutines.flow.first
import retrofit2.Response
import javax.inject.Inject


class SearchRepository @Inject constructor(
    private val service: DictionMasterService,
    private val dao: WordsDao,
    private val dataStore: DataStore<Preferences>
) {

    suspend fun search(word: String): List<WordDetail> {

        if(calculateSearches()){
            throw AllAttemptsMadeBuyAppException()
        }

        val result: List<WordDetail> = searchDao(word)

        result.let {
            if (it.isNullOrEmpty()) {
                return searchApi(word)
            }
        }
        return result
    }

    private fun searchDao(word: String): List<WordDetail> {
        val listReturned = mutableListOf<WordDetail>()

        dao.findWord(word)?.map { listWordDetailEntity ->
            transformFromJson(listWordDetailEntity, listReturned)
        }
        return listReturned.toList()
    }

    private fun transformFromJson(
        listWordDetailEntity: WordDetailEntity,
        listReturned: MutableList<WordDetail>
    ): MutableList<WordDetail> {
        listWordDetailEntity?.let { detailEntity ->
            val itemConverted = convertFromJsonGenericList<WordDetail>(detailEntity.json)
            listReturned.addAll(itemConverted ?: emptyList())
        }
        return listReturned
    }

    private suspend fun searchApi(word: String): List<WordDetail> {
        countableSumSearchesMade()
        return service.search(word).let { response ->
            when {
                response.isSuccessful -> {
                    dataTreatment(response, word)
                }
                else -> {
                    throw DataNotFoundException()
                }
            }

        }
    }

    private fun dataTreatment(
        response: Response<List<WordDetailResponse>>,
        word: String
    ) = response.body()?.let { listWordDetailResponse ->
        convertToJson(listWordDetailResponse, word)
        transform(listWordDetailResponse)
    } ?: run {
        emptyList()
    }

    private fun transform(listWordDetailResponse: List<WordDetailResponse>) =
        listWordDetailResponse.map { wordDetailResponse ->
            wordDetailResponse.toWordDetail()
        }

    private fun convertToJson(
        listWordDetailResponse: List<WordDetailResponse>,
        word: String
    ) {
        val json = convertToJsonGeneric(listWordDetailResponse)
        json?.let {
            dao.save(WordDetailEntity(word = word, json = json))
        }
    }

    private suspend fun calculateSearches(): Boolean = verifyIfUserCanContinueUseApp()


    private suspend fun verifyIfUserCanContinueUseApp(): Boolean {
        dataStore.data.first()[PreferencesKey.USER_QUANTITY_SEARCHS]?.let {
            if (it > 10) {
                return true
            }
        }
            return false
    }

    private suspend fun countableSumSearchesMade() {
        dataStore.edit {
            it[PreferencesKey.USER_QUANTITY_SEARCHS] =
                1 + (dataStore.data.first()[PreferencesKey.USER_QUANTITY_SEARCHS] ?: 0)
        }
    }

    suspend fun clean() {
        dataStore.edit {
            it[PreferencesKey.USER_QUANTITY_SEARCHS] = 0
        }
    }

    companion object {
        const val TAG = "SearchRepository"
    }
}

