import br.com.dictionmaster.model.Definition
import br.com.dictionmaster.model.Meaning
import br.com.dictionmaster.model.Phoneme
import br.com.dictionmaster.model.WordDetail
import br.com.dictionmaster.network.data.DefinitionResponse
import br.com.dictionmaster.network.data.MeaningResponse
import br.com.dictionmaster.network.data.PhonemeResponse
import br.com.dictionmaster.network.data.WordDetailResponse



const val ERROR = "error"

const val BOSS_SEARCH = "boss"

val RESPONSE = WordDetailResponse(
    word = "boss",
    phonetic = "",
    phonetics = listOf(PhonemeResponse(text = "", audio = "")),
    meanings = listOf(
        MeaningResponse(
            partOfSpeech = "",
            definitions = listOf(
                DefinitionResponse(
                    definition = "", example = "", synonyms = emptyList(), antonyms = emptyList()
                )
            )
        )
    )
)

val LIST_WORDS = listOf(
    WordDetail(
        word = "boss",
        phonetic = "/bɑs/",
        phonetics = listOf(
            Phoneme(
                text = "/bɑs/",
                audio = ""
            ),
            Phoneme(
                text = "/bɑs/",
                audio = ""
            ),
            Phoneme(
                text = "/bɑs/",
                audio = ""
            ),
        ),
        meanings = listOf(
            Meaning(
                partOfSpeech = "noun",
                definitions = listOf(
                    Definition(
                        definition = "A person who oversees and directs the work of others; a supervisor.",
                        synonyms = emptyList(),
                        antonyms = emptyList(),
                    ),
                    Definition(
                        definition = "Facts, skills and ideas that have been learned, either formally or informally,",
                        example = "Chat turned to whisper when the boss entered the conference room.",
                        synonyms = emptyList(),
                        antonyms = emptyList()
                    )
                )
            ),
            Meaning(
                partOfSpeech = "verb",
                definitions = listOf(
                    Definition(
                        definition = "1 Definition test.",
                        example = "1 Education verb test",
                        synonyms = emptyList(),
                        antonyms = emptyList()
                    ),
                    Definition(
                        definition = "2 Definition test.",
                        example = " 3 Education verb test",
                        synonyms = emptyList(),
                        antonyms = emptyList()
                    ),
                    Definition(
                        definition = "3 Definition test.",
                        example = " 3 Education verb test",
                        synonyms = emptyList(),
                        antonyms = emptyList()
                    ),

                    )
            )
        )
    ),
    WordDetail(
        word = "education",
        phonetic = "/ˌɛdjʊˈkeɪʃn̩/",
        phonetics = listOf(
            Phoneme(
                text = "/ˌɛdjʊˈkeɪʃn̩/",
                audio = "https://api.dictionaryapi.dev/media/pronunciations/en/education-uk.mp3"
            ),
            Phoneme(
                text = "/ˌɛdjʊˈkeɪʃn̩/",
                audio = "https://api.dictionaryapi.dev/media/pronunciations/en/education-uk.mp3"
            ),
        ),
        meanings = listOf(
            Meaning(
                partOfSpeech = "noun",
                definitions = listOf(
                    Definition(
                        definition = "The process of imparting knowledge, skill and judgment",
                        example = "Education is the slight hammer that breaks the yoke of ignorance, and moulds knowledge, skills, ideas, good moral values in a person be it a child, a youth or full grown adult. no matter a persons age learning never stops",
                        synonyms = emptyList(),
                        antonyms = emptyList()
                    ),
                    Definition(
                        definition = "Facts, skills and ideas that have been learned, either formally or informally,",
                        example = "He has had a classical education.",
                        synonyms = emptyList(),
                        antonyms = emptyList()
                    )
                )
            ),
            Meaning(
                partOfSpeech = "verb",
                definitions = listOf(
                    Definition(
                        definition = "1 Definition test.",
                        example = "1 Education verb test",
                        synonyms = emptyList(),
                        antonyms = emptyList()
                    ),
                    Definition(
                        definition = "2 Definition test.",
                        example = " 3 Education verb test",
                        synonyms = emptyList(),
                        antonyms = emptyList()
                    ),
                    Definition(
                        definition = "3 Definition test.",
                        example = " 3 Education verb test",
                        synonyms = emptyList(),
                        antonyms = emptyList()
                    ),

                    )
            )
        )
    )
)
