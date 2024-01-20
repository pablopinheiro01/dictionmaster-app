package br.com.dictionmaster.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.dictionmaster.ui.theme.buttonColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DictionMasterButtonComponent(
    modifier: Modifier = Modifier,
    textButton: String = "",
    onClickButton: () -> Unit = {}
) {
    Card(
        onClick = { onClickButton() },
        colors = CardDefaults
            .cardColors(
                containerColor = buttonColor
            )
    ) {
        Row(
            modifier = modifier
                .widthIn(358.dp)
                .heightIn(64.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = modifier.align(Alignment.CenterVertically),
                text = textButton,
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview
@Composable
fun DictionMasterButtonComponentPreview() {
    DictionMasterButtonComponent(textButton = "SEARCH")
}