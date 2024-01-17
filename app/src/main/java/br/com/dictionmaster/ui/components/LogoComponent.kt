package br.com.dictionmaster.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.dictionmaster.R

@Composable
fun LogoComponent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.icon_book),
            contentDescription = "book logo"
        )
        Image(
            modifier = Modifier.offset(x = 10.dp, y = -30.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.title_icon),
            contentDescription = "title logo"
        )
    }

}

@Preview
@Composable
fun LogoComponentPreview() {
    LogoComponent()
}