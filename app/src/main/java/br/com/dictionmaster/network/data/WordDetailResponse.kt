package br.com.dictionmaster.network.data

data class WordDetailResponse(
    val word: String,
    val phonetic: String? = "",
    val phonetics: List<PhonemeResponse>? = emptyList(),
    val meanings: List<MeaningResponse>? = emptyList()
)