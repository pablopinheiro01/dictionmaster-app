package br.com.dictionmaster.model

import br.com.dictionmaster.network.data.MeaningResponse
import br.com.dictionmaster.network.data.PhonemeResponse

data class WordDetail(
    val word: String,
    val phonetic: String,
    val phonetics: List<Phoneme>,
    val origin: String,
    val meanings: List<Meaning>
)