package br.com.dictionmaster.model

data class Meaning(
    val partOfSpeech: String,
    val definitions: List<Definition>
)