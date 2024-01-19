package br.com.dictionmaster.network.data

data class WordDetailResponse(
    val word: String,
    val phonetic: String,
    val phonetics: List<PhonemeResponse>,
    val origin: String,
    val meanings: List<MeaningResponse>
)