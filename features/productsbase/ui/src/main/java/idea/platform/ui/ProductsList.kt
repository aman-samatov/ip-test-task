package idea.platform.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import idea.platform.testtask.model.ProductItem
import idea.platform.testtask.model.SearchData
import idea.platform.testtask.model.State

@Composable
internal fun ProductList(
    modifier: Modifier,
    productsData: State.Data,
    onUpdateProduct: (ProductItem, ProductItem.Amount) -> Unit = { _, _ -> },
    onDeleteProduct: (ProductItem) -> Unit = {},
    onSearchDataChange: (SearchData) -> Unit = {},
) {
    ProductList(
        modifier = modifier,
        products = productsData.products,
        onUpdateProduct = onUpdateProduct,
        onDeleteProduct = onDeleteProduct,
        onSearchDataChange = onSearchDataChange
    )
}

@Composable
internal fun ProductList(
    modifier: Modifier,
    products: List<ProductItem>,
    onUpdateProduct: (ProductItem, ProductItem.Amount) -> Unit,
    onDeleteProduct: (ProductItem) -> Unit,
    onSearchDataChange: (SearchData) -> Unit
) {

    LazyColumn {
        item {
            ProductSearch(modifier, onSearchDataChange)
        }

        items(products, key = { it.id }) { product ->
            Product(
                modifier = modifier,
                product = product,
                onUpdateProduct = onUpdateProduct,
                onDeleteProduct = onDeleteProduct,
            )
        }
    }
}