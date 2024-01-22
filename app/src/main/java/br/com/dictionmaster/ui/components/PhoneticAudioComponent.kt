package br.com.dictionmaster.ui.components

import android.media.MediaPlayer
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.dictionmaster.R
import br.com.dictionmaster.ui.theme.DictionMasterTheme
import br.com.dictionmaster.ui.theme.buttonColor

@Composable
fun PhoneticAudioComponent(
    modifier: Modifier = Modifier,
    phonetic: String = "",
    audioLink: String = "",
    ) {
    val context = LocalContext.current
    val mediaPlayer = remember {
        MediaPlayer().apply {
            if (audioLink != "") {
                setDataSource(context, Uri.parse(audioLink))
                prepareAsync()
            }
        }
    }

    Box(
        modifier = modifier
            .size(46.dp)
            .clip(CircleShape)
            .background(buttonColor)
            .clickable {
                if(!mediaPlayer.isPlaying && (audioLink != "")){
                    mediaPlayer.start()
                }else{
                    mediaPlayer.pause()
                }
            }
    ) {
        Image(
            modifier = modifier
                .widthIn(22.dp)
                .heightIn(20.dp)
                .align(Alignment.Center),
            imageVector = ImageVector.vectorResource(R.drawable.audio_speaker_on),
            contentDescription = "",
        )
    }
    Spacer(modifier = modifier.widthIn(5.dp))
    Text(
        text = phonetic,
        fontSize = 22.sp,
        fontWeight = FontWeight.Normal,
        color = Color.Gray
    )
}

@Preview
@Composable
fun PhoneticAudioComponentPreview() {
    DictionMasterTheme {
        Surface {

            PhoneticAudioComponent(phonetic = "/ˌɛdjʊˈkeɪʃn̩/")
        }
    }
}