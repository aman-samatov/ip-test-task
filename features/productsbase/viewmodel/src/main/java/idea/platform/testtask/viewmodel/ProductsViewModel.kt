package idea.platform.testtask.viewmodel

import idea.platform.testtask.converter.convertList
import idea.platform.testtask.interactor.ProductsInteractor
import idea.platform.testtask.model.ProductItem
import idea.platform.testtask.model.ProductItemConverter
import idea.platform.testtask.model.SearchData
import idea.platform.testtask.model.State
import idea.platform.testtask.mvvm.BaseViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import timber.log.Timber
import kotlin.properties.Delegates

class ProductsViewModel internal constructor(
    private val interactor: ProductsInteractor,
    private val productConverter: ProductItemConverter,
) : BaseViewModel() {

    private var searchJob: Job? = null

    private var searchData: SearchData by Delegates.observable(SearchData.EMPTY_PARAMETERS) { _, old, new ->
        searchJob?.cancel()

        if (old != new && new.hasNotBlankParameters()) {
            observeAll()
        }
    }

    private val _productStateFlow = MutableStateFlow<State>(State.None)
    val productStateFlow = _productStateFlow.asStateFlow()

    fun observeAll() {
        searchJob = launch {
            try {
                interactor
                    .observeAll(searchData.name)
                    .collect { products ->
                        val productItems = products.convertList(productConverter)

                        if (productItems.isEmpty() && searchData.isEmpty()) {
                            _productStateFlow.update { State.None }
                            return@collect
                        }

                        _productStateFlow.update { State.Data(productItems) }
                    }
            } catch (c: CancellationException) {
                Timber.w("Products collection was cancelled")
            } catch (t: Throwable) {
                Timber.e(t, "Error while collecting products")
                _productStateFlow.update { State.Error(t) }
            }
        }
    }

    fun changeSearchParameters(data: SearchData) {
        searchData = data
    }

    fun update(productItem: ProductItem, productAmount: ProductItem.Amount) {
        launch {
            try {
                val product = productConverter.revert(productItem.copy(amount = productAmount))

                interactor.update(product)
            } catch (c: CancellationException) {
                Timber.w("Product insertion was cancelled")
            } catch (t: Throwable) {
                Timber.e(t, "Error while inserting product")
                _productStateFlow.update { State.Error(t) }
            }
        }
    }

    fun remove(productItem: ProductItem) {
        launch {
            try {
                val product = productConverter.revert(productItem)

                interactor.remove(product)
            } catch (c: CancellationException) {
                Timber.w("Product removing was cancelled")
            } catch (t: Throwable) {
                Timber.e(t, "Error while removing product")
                _productStateFlow.update { State.Error(t) }
            }
        }
    }
}