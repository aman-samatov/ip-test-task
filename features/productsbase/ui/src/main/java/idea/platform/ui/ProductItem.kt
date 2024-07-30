package idea.platform.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import idea.platform.testtask.model.ProductItem
import idea.platform.uikit.DarkBlue
import idea.platform.uikit.DarkOrange
import idea.platform.uikit.Typography
import idea.platform.uikit.widget.DefaultAlertDialog

@Composable
internal fun Product(
    modifier: Modifier,
    product: ProductItem,
    onUpdateProduct: (ProductItem, ProductItem.Amount) -> Unit,
    onDeleteProduct: (ProductItem) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 12.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { focusManager.clearFocus() }
    ) {
        Column(
            modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(8.dp)
        ) {
            ProductHeader(
                modifier = modifier,
                product = product,
                onUpdateProduct = onUpdateProduct,
                onDeleteProduct = onDeleteProduct
            )

            ProductTags(
                modifier = modifier,
                product = product
            )

            ProductInfo(
                modifier = modifier,
                product = product
            )
        }
    }
}

@SuppressLint("RememberReturnType")
@Composable
private fun ProductHeader(
    modifier: Modifier,
    product: ProductItem,
    onUpdateProduct: (ProductItem, ProductItem.Amount) -> Unit,
    onDeleteProduct: (ProductItem) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        var isProductChaneDialogVisible by rememberSaveable { mutableStateOf(false) }
        var isProductRemovingDialogVisible by rememberSaveable { mutableStateOf(false) }

        Text(
            text = product.name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = modifier.weight(1f)
        )

        Icon(
            painter = rememberVectorPainter(image = Icons.Filled.Create),
            tint = DarkBlue,
            contentDescription = null,
            modifier = modifier
                .clip(CircleShape)
                .clickable {
                    isProductChaneDialogVisible = !isProductChaneDialogVisible
                }
                .padding(6.dp)
        )

        Icon(
            painter = rememberVectorPainter(image = Icons.Filled.Delete),
            tint = DarkOrange,
            contentDescription = null,
            modifier = modifier
                .clip(CircleShape)
                .clickable { isProductRemovingDialogVisible = !isProductRemovingDialogVisible }
                .padding(6.dp)
        )

        if (isProductChaneDialogVisible) {
            ProductChangeDialog(
                modifier = modifier,
                productAmount = product.amount,
                onDismissRequest = { isProductChaneDialogVisible = !isProductChaneDialogVisible },
                onConfirmation = { newAmount ->
                    onUpdateProduct(product, newAmount)

                    isProductChaneDialogVisible = !isProductChaneDialogVisible
                }
            )
        }

        if (isProductRemovingDialogVisible)
            DefaultAlertDialog(
                modifier = modifier,
                dialogTitle = stringResource(id = R.string.product_item_removing_title),
                dialogText = stringResource(id = R.string.product_item_removing_description),
                icon = Icons.Default.Warning,
                onDismissRequest = {
                    isProductRemovingDialogVisible = !isProductRemovingDialogVisible
                },
                onConfirmation = { onDeleteProduct(product) }
            )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ProductTags(
    modifier: Modifier,
    product: ProductItem
) {
    if (product.hasTags()) {
        FlowRow(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        ) {
            product.tags.forEach {
                Text(
                    text = it,
                    style = Typography.bodyMedium,
                    modifier = modifier.tagModifier()
                )
            }
        }
    }
}

@Composable
private fun ProductInfo(
    modifier: Modifier,
    product: ProductItem,
    textSize: TextUnit = 14.sp,
) {
    Row(modifier.padding(top = 8.dp)) {
        Column(modifier = modifier.weight(1f)) {
            Text(
                text = stringResource(id = R.string.product_stock),
                fontWeight = FontWeight.Medium,
                fontSize = textSize
            )

            Text(
                text = product.amount.printValue(),
                fontSize = textSize
            )
        }

        Column(modifier = modifier.weight(1f)) {
            Text(
                text = stringResource(id = R.string.product_addition_date),
                fontWeight = FontWeight.Medium,
                fontSize = textSize
            )

            Text(
                text = product.getDate(),
                fontSize = textSize
            )
        }
    }
}

private fun Modifier.tagModifier(): Modifier {
    return this
        .padding(top = 4.dp, end = 4.dp)
        .clip(RoundedCornerShape(8.dp))
        .border(
            width = 1.dp,
            color = Color.Black,
            shape = RoundedCornerShape(8.dp),
        )
        .clickable { }
        .padding(horizontal = 16.dp, vertical = 6.dp)
}

