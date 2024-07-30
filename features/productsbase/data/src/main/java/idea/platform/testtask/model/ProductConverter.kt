package idea.platform.testtask.model

import idea.platform.testtask.converter.BaseDataConverter

internal class ProductConverter : BaseDataConverter<ProductDBO, Product> {

    override fun convert(response: ProductDBO): Product {
        return Product(
            id = response.id,
            name = response.name,
            time = response.time,
            tags = response.tags,
            amount = response.amount,
        )
    }

    override fun revert(data: Product): ProductDBO {
        return ProductDBO(
            id = data.id,
            name = data.name,
            time = data.time,
            tags = data.tags,
            amount = data.amount,
        )
    }
}