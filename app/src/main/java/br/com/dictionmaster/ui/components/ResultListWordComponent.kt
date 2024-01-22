package br.com.dictionmaster.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.dictionmaster.model.WordDetail
import br.com.dictionmaster.ui.theme.DictionMasterTheme
import br.com.dictionmaster.ui.theme.textBlue

@Composable
fun ResultListWordComponent(
    modifier: Modifier = Modifier,
    wordDetail: WordDetail
) {
    Text(
        text = wordDetail.word,
        fontSize = 45.sp,
        fontWeight = FontWeight.Bold,
        color = textBlue
    )
    Row(
        modifier.padding(top = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PhoneticAudioComponent(phonetic = wordDetail.phonetic, audioLink = wordDetail.phonetics?.first()?.audio ?: "")
    }

    Row(
        modifier.padding(top = 10.dp)
    ) {
        MeaningComponent()
    }

    Row(modifier.padding(top = 10.dp)) {
        Column {
            Row {
                MeaningExampleComponent()
            }
        }
    }
}

@Preview
@Composable
fun ResultListWordComponentPreview() {
    DictionMasterTheme {
        Surface {
            ResultListWordComponent(wordDetail = WordDetail())
        }
    }
}