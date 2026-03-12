package com.pba.product.catalog.domain.usecase

import com.pba.product.catalog.domain.model.Product
import com.pba.product.catalog.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class SearchProductsUseCase(
    private val productRepository: ProductRepository
) {
    operator fun invoke(keyword: String): Flow<List<Product>> = productRepository.searchProducts(keyword)
}