package idea.platform.uikit.widget

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun ErrorToast(text: String) {

    Toast.makeText(LocalContext.current, text, Toast.LENGTH_SHORT).show()
}

@Composable
fun ErrorToast(error: Throwable) {
    val context = LocalContext.current

    Toast
        .makeText(context, error.message, Toast.LENGTH_SHORT)
        .show()
}