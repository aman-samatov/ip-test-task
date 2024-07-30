package idea.platform.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import idea.platform.testtask.model.ProductItem
import idea.platform.uikit.Grey
import idea.platform.uikit.PurpleGrey

@Composable
internal fun ProductChangeDialog(
    modifier: Modifier,
    productAmount: ProductItem.Amount,
    onDismissRequest: () -> Unit,
    onConfirmation: (ProductItem.Amount) -> Unit,
) {
    var amount by remember {
        mutableStateOf(productAmount)
    }

    AlertDialog(
        modifier = modifier,

        icon = {
            Icon(Icons.Default.Settings, tint = Grey, contentDescription = null)
        },

        title = {
            Text(stringResource(id = R.string.product_item_change_title))
        },

        text = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_minus),
                    tint = PurpleGrey,
                    contentDescription = null,
                    modifier = modifier
                        .clip(CircleShape)
                        .clickable { amount = amount.decrement() }
                        .padding(6.dp)
                )
                Text(
                    text = amount.printValue(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    modifier = modifier.padding(horizontal = 20.dp)
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_plus),
                    tint = PurpleGrey,
                    contentDescription = null,
                    modifier = modifier
                        .clip(CircleShape)
                        .clickable { amount = amount.increment() }
                        .padding(6.dp)
                )
            }
        },

        onDismissRequest = {
            onDismissRequest()
        },

        confirmButton = {
            TextButton(
                colors = ButtonDefaults.textButtonColors(contentColor = PurpleGrey),
                onClick = {
                    onConfirmation(amount)
                }
            ) {
                Text(stringResource(id = R.string.product_item_change_positive))
            }
        },

        dismissButton = {
            TextButton(
                colors = ButtonDefaults.textButtonColors(contentColor = PurpleGrey),
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(stringResource(id = R.string.product_item_change_negative))
            }
        }
    )
}