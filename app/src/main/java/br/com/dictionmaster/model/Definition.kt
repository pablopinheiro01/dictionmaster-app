package br.com.dictionmaster.model

import br.com.dictionmaster.network.data.DefinitionResponse

data class Definition(
    val definition: String? = null,
    val example: String? = null,
    val synonyms: List<String>?,
    val antonyms: List<String>?
)

fun DefinitionResponse.toDefinition(): Definition{
    return Definition(
        definition = definition,
        example = example,
        synonyms = synonyms,
        antonyms = antonyms
    )
}