import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.dictionmaster.R
import br.com.dictionmaster.ui.theme.gray
import br.com.dictionmaster.ui.theme.textBlue

@Composable
fun LanguageSelectedComponent(
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp)),
        colors = CardDefaults
            .cardColors(
                containerColor = gray
            ),

        ) {
        Row(
            modifier = modifier
                .widthIn(130.dp)
                .heightIn(40.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.england),
                contentDescription = stringResource(id = R.string.english_selected),
            )
            Spacer(modifier = modifier.widthIn(5.dp))
            Text(
                text = stringResource(id = R.string.english_selected),
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                color = textBlue
            )
        }
    }
}

@Preview
@Composable
fun LanguageSelectedComponentPreview() {
    LanguageSelectedComponent()
}