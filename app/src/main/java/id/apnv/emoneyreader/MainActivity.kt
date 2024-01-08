package id.apnv.emoneyreader

import android.content.Context
import android.nfc.NfcManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import id.apnv.emoneyreader.ui.theme.EmoneyReaderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmoneyReaderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val nfcManager = this.getSystemService(Context.NFC_SERVICE) as NfcManager
                    NfcTool(nfcManager)
                }
            }
        }
    }
}

@Composable
fun NfcTool(nfcManager: NfcManager?, modifier: Modifier = Modifier) {
    var status by remember { mutableStateOf("IDLE") }
    var nfcReady by remember { mutableStateOf("NFC not ready") }

    if (nfcManager != null && nfcManager.defaultAdapter.isEnabled) {
        nfcReady = "NFC is ready"
    }

    val onClickHandler = {
        status = "WAIT"
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    )
    {
        Text(
            text = nfcReady,
            modifier = modifier
        )
        Button(
            onClick = onClickHandler
        ) {
            Text(text = "Read Tag")
        }
        Text(
            text = "Status: $status",
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EmoneyReaderTheme {
        NfcTool(null)
    }
}
