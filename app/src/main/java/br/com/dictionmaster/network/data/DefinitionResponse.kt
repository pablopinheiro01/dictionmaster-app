package br.com.dictionmaster.network.data

data class DefinitionResponse(
    val definition: String,
    val example: String,
    val synonyms: List<String>?,
    val antonyms: List<String>?
)