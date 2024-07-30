package idea.platform.testtask.interactor

import idea.platform.testtask.model.Product
import idea.platform.testtask.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow

internal class ProductsInteractor(
    private val productsRepository: ProductsRepository
) {

    fun observeAll(name: String): Flow<List<Product>> {
        return productsRepository.observeAll(name)
    }

    suspend fun update(product: Product) {
        productsRepository.update(product)
    }

    suspend fun remove(product: Product) {
        productsRepository.remove(product)
    }

}