package idea.platform.testtask.model

import idea.platform.testtask.converter.BaseDataConverter

internal class ProductItemConverter : BaseDataConverter<Product, ProductItem> {

    override fun convert(response: Product): ProductItem {
        return ProductItem(
            id = response.id,
            name = response.name,
            time = response.time,
            tags = response.tags,
            amount = ProductItem.Amount(response.amount),
        )
    }

    override fun revert(data: ProductItem): Product {
        return Product(
            id = data.id,
            name = data.name,
            time = data.time,
            tags = data.tags,
            amount = data.amount.amount,
        )
    }
}