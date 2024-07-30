package idea.platform.testtask.repository

import idea.platform.testtask.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    fun observeAll(name: String): Flow<List<Product>>

    suspend fun update(product: Product)

    suspend fun remove(product: Product)
}