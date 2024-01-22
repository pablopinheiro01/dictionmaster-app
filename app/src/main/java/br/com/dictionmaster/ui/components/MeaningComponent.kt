package br.com.dictionmaster.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import br.com.dictionmaster.ui.theme.textBlue

@Composable
fun MeaningComponent(
    number:String = "",
    partOfSpeech: String = "",
    definitions: String = ""
) {
    Text(
        text = buildAnnotatedString {
            this.withStyle(style = SpanStyle(color = textBlue)) {
                append("$number)")
            }
            withStyle(style = SpanStyle(color = Color.Gray)) {
                append(" $partOfSpeech ")
            }
            withStyle(style = SpanStyle(color = textBlue)) {
                append("$definitions")
            }
        },
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        color = textBlue
    )
}

@Preview
@Composable
fun MeaningComponentPreview() {
    MeaningComponent()
}