package com.pba.product.catalog.ui.contract

import com.pba.product.catalog.domain.model.Product


data class ProductListState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String? = null
)

sealed interface ProductListIntent {
    data class SearchProducts(val keyword: String) : ProductListIntent
    data class OnProductClick(val product: Product) : ProductListIntent
    data class SelectCategory(val category: String?) : ProductListIntent
}

sealed interface ProductListEffect {
    data class NavigateToProductDetails(val productId: Int) : ProductListEffect
}