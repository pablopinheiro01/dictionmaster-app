package br.com.dictionmaster.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.dictionmaster.R
import br.com.dictionmaster.ui.components.DictionMasterButtonComponent
import br.com.dictionmaster.ui.components.LogoComponent
import br.com.dictionmaster.ui.theme.DictionMasterTheme
import br.com.dictionmaster.ui.theme.textBlue
import br.com.dictionmaster.ui.theme.buttonColor as TextBlueLight

@Composable
fun PurchaseScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier.fillMaxSize(),
    ) {
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
        Column(
            modifier.offset(y=(-70).dp),
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoComponent()
            Column(
                modifier = modifier.padding(start = 15.dp, end = 15.dp, bottom = 20.dp)
            ){
                FinalTextPurchaseComponent()
            }
            DictionMasterButtonComponent(textButton = "SUBSCRIBE")
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
                append("Subscribe now to get ")
            }
            withStyle(
                style = SpanStyle(
                    color = TextBlueLight,
                )
            ) {
                append("unlimited ")
            }
            withStyle(
                style = SpanStyle(
                    color = textBlue,
                )
            ) {
                append("searches and full access to ")
            }
            withStyle(
                style = SpanStyle(
                    color = TextBlueLight,
                )
            ) {
                append("all features")
            }
            withStyle(
                style = SpanStyle(
                    color = textBlue,
                )
            ) {
                append(".")
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
                append("Try 7 Days Free, ")
            }
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Light
                )
            ) {
                append("then only")
            }
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.SemiBold
                )
            ) {
                append(" $19,99 ")
            }
            withStyle(
                style = SpanStyle(fontWeight = FontWeight.Light)
            ) {
                append("per year. Cancel anytime.")
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