package br.com.dictionmaster.model

import br.com.dictionmaster.network.data.PhonemeResponse

data class Phoneme(
    val text: String?,
    val audio: String?
)

fun PhonemeResponse.toPhoneme(): Phoneme{
    return Phoneme(
        text = text,
        audio = audio
    )
}