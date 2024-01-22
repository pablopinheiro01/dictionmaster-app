package br.com.dictionmaster.network.data

data class MeaningResponse(
    val partOfSpeech: String? = "",
    val definitions: List<DefinitionResponse>? = emptyList()
)