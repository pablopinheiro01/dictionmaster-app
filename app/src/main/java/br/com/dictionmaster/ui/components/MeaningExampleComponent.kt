package br.com.dictionmaster.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import br.com.dictionmaster.R
import br.com.dictionmaster.ui.theme.textBlue

@Composable
fun MeaningExampleComponent(
    example: String = ""
) {
    Text(
        text = stringResource(id = R.string.text_mark),
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = textBlue
    )

    Text(
        text = example,
        fontSize = 16.sp,
        fontWeight = FontWeight.Light,
        color = textBlue
    )
}

@Preview
@Composable
fun MeaningExampleComponentPreview() {
    MeaningExampleComponent()
}