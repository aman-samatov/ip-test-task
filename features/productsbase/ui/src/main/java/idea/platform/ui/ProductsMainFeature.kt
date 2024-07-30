package idea.platform.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import idea.platform.testtask.model.ProductItem
import idea.platform.testtask.model.SearchData
import idea.platform.testtask.model.State
import idea.platform.testtask.viewmodel.ProductsViewModel
import idea.platform.uikit.TestTaskTheme
import idea.platform.uikit.widget.ErrorToast
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductsMainScreen(modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<ProductsViewModel>()
    ProductsMainScreen(viewModel = viewModel, modifier = modifier)
    viewModel.observeAll()
}

@Composable
internal fun ProductsMainScreen(
    viewModel: ProductsViewModel,
    modifier: Modifier,
) {
    val state by viewModel.productStateFlow.collectAsState()
    val currentState = state

    NewsMainContent(
        currentState = currentState,
        modifier = modifier,
        onUpdateProduct = viewModel::update,
        onDeleteProduct = viewModel::remove,
        onSearchDataChange = viewModel::changeSearchParameters,
    )
}

@Composable
private fun NewsMainContent(
    currentState: State,
    modifier: Modifier,
    onUpdateProduct: (ProductItem, ProductItem.Amount) -> Unit = { _, _ -> },
    onDeleteProduct: (ProductItem) -> Unit = {},
    onSearchDataChange: (SearchData) -> Unit = {},
) {
    Column(modifier.fillMaxSize()) {
        Text(
            text = stringResource(id = R.string.products_list),
            style = TestTaskTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
                .background(color = TestTaskTheme.colorScheme.primary)
                .padding(top = 26.dp, bottom = 16.dp)
        )

        when (currentState) {
            is State.None -> EmptyDatabaseNotification(modifier)

            is State.Error -> ErrorMessage(currentState)

            is State.Data -> ProductList(
                modifier = modifier,
                productsData = currentState,
                onUpdateProduct = onUpdateProduct,
                onDeleteProduct = onDeleteProduct,
                onSearchDataChange = onSearchDataChange,
            )
        }
    }
}

@Composable
private fun EmptyDatabaseNotification(modifier: Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(
            text = stringResource(id = R.string.products_empty_base),
            modifier = modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun ErrorMessage(
    state: State.Error,
) {
    ErrorToast(state.error)
}
