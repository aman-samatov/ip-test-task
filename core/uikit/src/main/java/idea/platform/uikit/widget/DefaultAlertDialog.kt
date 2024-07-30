package idea.platform.uikit.widget

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import idea.platform.uikit.Grey
import idea.platform.uikit.PurpleGrey
import idea.platform.uikit.R

@Composable
fun DefaultAlertDialog(
    modifier: Modifier,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    AlertDialog(
        modifier = modifier,
        icon = {
            Icon(icon, tint = Grey, contentDescription = null)
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                colors = ButtonDefaults.textButtonColors(contentColor = PurpleGrey),
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(stringResource(id = R.string.dialog_positive))
            }
        },
        dismissButton = {
            TextButton(
                colors = ButtonDefaults.textButtonColors(contentColor = PurpleGrey),
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(stringResource(id = R.string.dialog_negative))
            }
        }
    )
}