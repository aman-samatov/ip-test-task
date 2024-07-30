package idea.platform.testtask.repository

import idea.platform.testtask.converter.convertList
import idea.platform.testtask.database.ProductsDatabase
import idea.platform.testtask.model.Product
import idea.platform.testtask.model.ProductConverter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class ProductsRepositoryImpl(
    private val database: ProductsDatabase,
    private val productConverter: ProductConverter,
) : ProductsRepository {

    override fun observeAll(name: String): Flow<List<Product>> {
        val data = database.productDao.observeAll("%$name%")

        return data.map { products ->
            products.convertList(productConverter)
        }
    }

    override suspend fun update(product: Product) {
        val data = productConverter.revert(product)

        database.productDao.update(data)
    }

    override suspend fun remove(product: Product) {
        val data = productConverter.revert(product)

        database.productDao.remove(data)
    }
}