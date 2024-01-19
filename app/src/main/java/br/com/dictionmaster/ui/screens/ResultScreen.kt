package br.com.dictionmaster.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.dictionmaster.ui.components.MeaningComponent
import br.com.dictionmaster.ui.components.MeaningExampleComponent
import br.com.dictionmaster.ui.components.PhoneticAudioComponent
import br.com.dictionmaster.ui.components.DictionMasterButtonComponent
import br.com.dictionmaster.ui.theme.DictionMasterTheme
import br.com.dictionmaster.ui.theme.textBlue as TextBlueColor

@Composable
fun ResultScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp)
        ) {
            Text(
                text = "Education",
                fontSize = 45.sp,
                fontWeight = FontWeight.Bold,
                color = TextBlueColor
            )
            Row(
                modifier.padding(top = 10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PhoneticAudioComponent()
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
                    Row {
                        MeaningExampleComponent()
                    }
                    Row {
                        MeaningExampleComponent()
                    }
                    Row {
                        MeaningExampleComponent()
                    }
                }
            }
        }

        Column(
            modifier = modifier
                .weight(1f, false)
                .padding(bottom = 30.dp)
        ) {
            Divider(
                modifier.padding(bottom = 20.dp),
                color = Color.Gray,
                thickness = 1.dp
            )
            Column(
                modifier = modifier
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                Text(
                    modifier = modifier.padding(bottom = 10.dp),
                    text = "That's it for \"education\" !",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextBlueColor
                )
                Text(
                    modifier = modifier.padding(bottom = 15.dp),
                    text = "Try another search now!",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    color = TextBlueColor
                )
                DictionMasterButtonComponent()
            }
        }
    }
}

@Preview
@Composable
fun ResultScreenPreview() {
    DictionMasterTheme {
        Surface {
            ResultScreen()
        }
    }
}