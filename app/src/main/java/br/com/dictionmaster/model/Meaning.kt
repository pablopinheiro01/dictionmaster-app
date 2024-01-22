package br.com.dictionmaster.model

import br.com.dictionmaster.network.data.MeaningResponse

data class Meaning(
    val partOfSpeech: String = "",
    val definitions: List<Definition>?
)

fun MeaningResponse.toMeaning(): Meaning{
    return Meaning(
        partOfSpeech = partOfSpeech?: "",
        definitions = definitions?.map { it.toDefinition() }
    )
}