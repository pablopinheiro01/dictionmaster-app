package br.com.dictionmaster.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.dictionmaster.R
import br.com.dictionmaster.repositories.SearchRepository
import br.com.dictionmaster.ui.components.DictionMasterButtonComponent
import br.com.dictionmaster.ui.components.LogoComponent
import br.com.dictionmaster.ui.theme.DictionMasterTheme
import br.com.dictionmaster.ui.theme.textBlue
import br.com.dictionmaster.ui.viewmodels.PurchaseScreenViewModel
import br.com.dictionmaster.ui.theme.buttonColor as TextBlueLight

@Composable
fun PurchaseScreen(
    viewModel: PurchaseScreenViewModel
) {
    PurchaseScreen(
        onResetCounter = {
            viewModel.cleanData()
        }
    )

}

@Composable
fun PurchaseScreen(
    modifier: Modifier = Modifier,
    onResetCounter: () -> Unit = {}
) {
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Row {
                Box(
                    modifier = modifier,
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Image(
                        modifier = modifier.fillMaxWidth(),
                        painter = painterResource(id = R.drawable.woman_finish_screen),
                        contentDescription = "",
                    )
                }
            }
            Row {
                Column(
                    modifier
                        .offset(y = (-70).dp)
                        .clickable {
                            onResetCounter()
                        },
                    verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LogoComponent()
                    Column(
                        modifier = modifier.padding(start = 15.dp, end = 15.dp, bottom = 20.dp)
                    ) {
                        FinalTextPurchaseComponent()
                    }
                }
            }
        }
        Row(
            modifier.padding(20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            DictionMasterButtonComponent(textButton = stringResource(id = R.string.button_subscribe))
        }
    }
}


@Composable
fun FinalTextPurchaseComponent(
    modifier: Modifier = Modifier
) {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = textBlue,
                )
            ) {
                append(stringResource(id = R.string.subscribe_text_1))
            }
            withStyle(
                style = SpanStyle(
                    color = TextBlueLight,
                )
            ) {
                append(" ")
                append(stringResource(id = R.string.subscribe_text_2))
            }
            withStyle(
                style = SpanStyle(
                    color = textBlue,
                )
            ) {
                append(" ")
                append(stringResource(id = R.string.subscribe_text_3))
            }
            withStyle(
                style = SpanStyle(
                    color = TextBlueLight,
                )
            ) {
                append(" ")
                append(stringResource(id = R.string.subscribe_text_4))
            }
            withStyle(
                style = SpanStyle(
                    color = textBlue,
                )
            ) {
                append(stringResource(id = R.string.dot_final))
            }
        },
        fontSize = 20.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )
    Text(
        modifier = modifier.padding(top = 20.dp),
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.SemiBold
                )
            ) {
                append(stringResource(id = R.string.subscribe_try_1))
            }
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Light
                )
            ) {
                append(" ")
                append(stringResource(id = R.string.subscribe_try_2))
            }
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.SemiBold
                )
            ) {
                append(" ")
                append(stringResource(id = R.string.subscribe_try_value))
            }
            withStyle(
                style = SpanStyle(fontWeight = FontWeight.Light)
            ) {
                append(" ")
                append(stringResource(id = R.string.subscribe_final))
            }
        },
        fontSize = 16.sp,
        color = textBlue,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
fun PurchaseScreenPreview() {
    DictionMasterTheme {
        Surface {
            PurchaseScreen()
        }
    }
}