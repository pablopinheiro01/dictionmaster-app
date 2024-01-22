package br.com.dictionmaster.model

import br.com.dictionmaster.network.data.MeaningResponse
import br.com.dictionmaster.network.data.PhonemeResponse
import br.com.dictionmaster.network.data.WordDetailResponse

data class WordDetail(
    val word: String = "",
    val phonetic: String = "",
    val phonetics: List<Phoneme>? = emptyList(),
    val meanings: List<Meaning>? = emptyList()
)

fun WordDetailResponse.toWordDetail(): WordDetail{
    return WordDetail(
        word = word,
        phonetic = phonetic ?: "",
        phonetics = phonetics?.map { it.toPhoneme() },
        meanings = meanings?.map { it.toMeaning() }
    )
}