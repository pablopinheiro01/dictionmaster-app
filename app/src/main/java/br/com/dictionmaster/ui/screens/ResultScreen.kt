package br.com.dictionmaster.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.dictionmaster.R
import br.com.dictionmaster.model.Definition
import br.com.dictionmaster.model.Meaning
import br.com.dictionmaster.model.Phoneme
import br.com.dictionmaster.model.WordDetail
import br.com.dictionmaster.navigation.navigateToPurchaseScreen
import br.com.dictionmaster.ui.components.DictionMasterButtonComponent
import br.com.dictionmaster.ui.components.MeaningComponent
import br.com.dictionmaster.ui.components.MeaningExampleComponent
import br.com.dictionmaster.ui.components.PhoneticAudioComponent
import br.com.dictionmaster.ui.theme.DictionMasterTheme
import br.com.dictionmaster.ui.theme.textBlue
import br.com.dictionmaster.ui.uistates.ResultScreenUiState
import br.com.dictionmaster.ui.theme.textBlue as TextBlueColor

@Composable
fun ResultScreen(
    state: ResultScreenUiState,
    navController: NavController
) {
    ResultScreen(
        state = state,
        onClickButton = {
            navController.navigateUp()
        },
        onNavigateToBuy = {
            navController.navigateToPurchaseScreen()
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    modifier: Modifier = Modifier,
    state: ResultScreenUiState,
    onClickButton: () -> Unit = {},
    onNavigateToBuy: () -> Unit = {}
) {
    Scaffold(
        bottomBar = {
            when (state) {
                is ResultScreenUiState.SuccessResultScreenUiState -> {
                    Column(
                        modifier = modifier
                            .background(color = Color.White)
                    ) {
                        Divider(
                            modifier.padding(bottom = 20.dp),
                            color = Color.Gray,
                            thickness = 1.dp
                        )
                        Column(
                            modifier = modifier
                                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                        ) {
                            state.listWords.first()?.word?.let { string ->
                                Text(
                                    modifier = modifier.padding(bottom = 10.dp),
                                    text = stringResource(id = R.string.result_screen_1, string),
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextBlueColor
                                )
                            }
                            Text(
                                modifier = modifier.padding(bottom = 15.dp),
                                text = stringResource(id = R.string.result_screen_2),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Light,
                                color = TextBlueColor
                            )
                            DictionMasterButtonComponent(
                                textButton = stringResource(id = R.string.new_search),
                                onClickButton = { onClickButton() },
                            )
                        }
                    }
                }

                is ResultScreenUiState.NavigateToBuy -> {
                    LaunchedEffect(null) {
                        onNavigateToBuy()
                    }
                }

                else -> {}
            }
        }
    ) { paddingValues ->
        when (state) {
            ResultScreenUiState.Error -> {
                Column(
                    modifier = modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(
                        25.dp,
                        alignment = Alignment.CenterVertically
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.result_screen_3),
                        fontSize = 16.sp,
                        color = Color.Red,
                        fontWeight = FontWeight.SemiBold
                    )
                    DictionMasterButtonComponent(
                        textButton = stringResource(id = R.string.new_search),
                        onClickButton = { onClickButton() },
                    )
                }
            }

            ResultScreenUiState.Loading -> {
                Column(
                    modifier = modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }

            is ResultScreenUiState.SuccessResultScreenUiState -> {
                LazyColumn(
                    modifier
                        .padding(paddingValues)
                        .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                ) {
                    item {
                        state.listWords.first()?.let { wordDetailItem ->
                            Text(
                                text = wordDetailItem.word,
                                fontSize = 45.sp,
                                fontWeight = FontWeight.Bold,
                                color = textBlue
                            )
                            Row(
                                modifier.padding(top = 10.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                PhoneticAudioComponent(
                                    phonetic = wordDetailItem.phonetic,
                                    audioLink = wordDetailItem.phonetics?.last()?.audio ?: ""
                                )
                            }
                        }

                    }

                    itemsIndexed(state.listWords) { index, item ->
                        item?.let {
                            Row(
                                modifier.padding(top = 10.dp)
                            ) {

                                item.meanings?.let { meanings ->
                                    item.meanings.flatMap {
                                        it.definitions ?: emptyList()
                                    }.let { definitions ->
                                        MeaningComponent(
                                            number = (index + 1).toString(),
                                            partOfSpeech = meanings.joinToString(", ") { it.partOfSpeech },
                                            definitions = definitions.joinToString(" ,") {
                                                it.definition ?: ""
                                            }
                                        )
                                    }
                                }
                            }

                            Row(modifier.padding(top = 10.dp)) {
                                Column {
                                    item.meanings?.forEach { meaning ->
                                        meaning.definitions?.forEach { definition ->
                                            definition.example?.let { example ->
                                                Row {
                                                    MeaningExampleComponent(
                                                        example = example
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }

                    }
                }
            }

            else -> {}
        }
    }
}

@Preview
@Composable
fun ResultScreenPreviewEducation() {

    ResultScreen(
        state = ResultScreenUiState.SuccessResultScreenUiState(
            listOf(
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
        )
    )


}

@Preview
@Composable
fun ResultScreenPreviewBoss() {
    DictionMasterTheme {
        Surface {
            ResultScreen(
                state = ResultScreenUiState.SuccessResultScreenUiState(
                    listOf(
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
                )
            )
        }
    }
}

@Preview
@Composable
fun ResultScreenPreviewError() {
    DictionMasterTheme {
        Surface {
            ResultScreen(
                state = ResultScreenUiState.Error
            )
        }
    }

}