package br.com.dictionmaster.repositories

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import br.com.dictionmaster.database.dao.WordsDao
import br.com.dictionmaster.database.entities.WordDetailEntity
import br.com.dictionmaster.model.WordDetail
import br.com.dictionmaster.model.toWordDetail
import br.com.dictionmaster.network.services.DictionMasterService
import br.com.dictionmaster.preferences.PreferencesKey
import br.com.dictionmaster.util.convertFromJsonGeneric
import br.com.dictionmaster.util.convertToJsonGeneric
import kotlinx.coroutines.flow.first
import javax.inject.Inject


class SearchRepository @Inject constructor(
    private val service: DictionMasterService,
    private val dao: WordsDao,
    private val dataStore: DataStore<Preferences>
) {

    suspend fun search(word: String): List<WordDetail?> {

        if(calculateSearchs()){
            throw Exception()
        }

        return try {

            val resultDao = searchDao(word)

            if (resultDao.isNullOrEmpty()) {
                searchApi(word)
            }
            resultDao

        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("SearchRepository", "error $e")
            emptyList()
        }


    }

    private suspend fun searchDao(word: String): List<WordDetail?> {
        val list = mutableListOf<WordDetail?>()

        dao.findWord(word).let { listWord ->
            listWord.forEach { word ->
                val converted = convertFromJsonGeneric<WordDetail>(word.json)
                list.add(converted)
            }
        }

        return list.toList()
    }

    private suspend fun searchApi(word: String): List<WordDetail?> {
        service.search(word).let { response ->
            Log.i("SearchRepository", "search: ${response.body()}")

            when {
                response.isSuccessful -> {
                    response.body()?.map { listWordDetailResponse ->
                        val json = convertToJsonGeneric(listWordDetailResponse)
                        json?.let {
                            dao.save(WordDetailEntity(word = word, json = json))
                        }
                    }
                    return response.body()?.map { wordDetailResponse ->
                        wordDetailResponse.toWordDetail()
                    } ?: run {
                        emptyList()
                    }
                }

                else -> {
                    return emptyList()
                }
            }

        }
    }

    private suspend fun calculateSearchs(): Boolean{
        dataStore.edit {
            it[PreferencesKey.USER_QUANTITY_SEARCHS] = 1
//            it[PreferencesKey.USER_QUANTITY_SEARCHS] = 1+(dataStore.data.first()[PreferencesKey.USER_QUANTITY_SEARCHS]?:0)
        }
        dataStore.data.first()[PreferencesKey.USER_QUANTITY_SEARCHS]?.let{
            Log.i("SearchRepository", "calculateSearchs: $it")
            if(it > 10){
                return true
            }
        }
        return false
    }
}

