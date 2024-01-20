package br.com.dictionmaster.repositories

import android.util.Log
import br.com.dictionmaster.database.dao.WordsDao
import br.com.dictionmaster.database.entities.WordDetailEntity
import br.com.dictionmaster.model.WordDetail
import br.com.dictionmaster.model.toWordDetail
import br.com.dictionmaster.network.services.DictionMasterService
import br.com.dictionmaster.util.convertFromJsonGeneric
import br.com.dictionmaster.util.convertToJsonGeneric
import javax.inject.Inject


class SearchRepository @Inject constructor(
    private val service: DictionMasterService,
    private val dao: WordsDao
) {

    suspend fun search(word: String): List<WordDetail?> {

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
}

