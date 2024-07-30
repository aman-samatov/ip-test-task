package idea.platform.testtask.model

sealed class State(open val products: List<ProductItem>?) {
    data object None : State(products = null)

    data class Error(
        val error: Throwable
    ) : State(products = null)

    class Data(
        override val products: List<ProductItem>
    ) : State(products = products)
}