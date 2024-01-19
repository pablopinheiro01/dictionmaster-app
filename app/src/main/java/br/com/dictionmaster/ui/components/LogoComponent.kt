package br.com.dictionmaster.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.dictionmaster.R
import br.com.dictionmaster.ui.theme.DictionMasterTheme


@Composable
fun LogoComponent(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.height(150.dp).width(188.dp)
    ) {
        Image(
            modifier = modifier.align(Alignment.TopCenter),
            imageVector = ImageVector.vectorResource(id = R.drawable.icon_book),
            contentDescription = "book logo"
        )
        Image(
            modifier = Modifier.offset(x = 15.dp, y= 15.dp).align(Alignment.BottomCenter),
            imageVector = ImageVector.vectorResource(id = R.drawable.title_icon),
            contentDescription = "title logo"
        )
    }

}
@Composable
fun LogoComponent1(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.icon_book),
            contentDescription = "book logo"
        )
        Image(
            modifier = Modifier.offset(x = 14.dp, y = (-50).dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.title_icon),
            contentDescription = "title logo"
        )
    }

}

@Preview
@Composable
fun LogoComponentPreview() {
    DictionMasterTheme {
        Surface {
            LogoComponent()
        }
    }
}