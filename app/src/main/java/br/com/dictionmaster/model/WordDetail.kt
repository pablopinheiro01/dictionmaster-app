package br.com.dictionmaster.model

data class WordDetail(
    val word: String,
    val phonetic: String,
    val phonetics: List<Phoneme>,
    val origin: String,
    val meanings: List<Meaning>
)