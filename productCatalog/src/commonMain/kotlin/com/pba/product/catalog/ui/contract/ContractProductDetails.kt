package com.pba.product.catalog.ui.contract

import com.pba.product.catalog.domain.model.Product

data class ProductDetailsState(
    val isLoading: Boolean = false,
    val product: Product? = null,
    val error: String? = null
)

sealed interface ProductDetailsIntent {
    data class LoadProduct(val productId: Int) : ProductDetailsIntent
    object OnNavigateBack : ProductDetailsIntent
}

sealed class ProductDetailsEffect {
    object NavigateBack : ProductDetailsEffect()
}