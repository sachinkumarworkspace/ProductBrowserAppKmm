package com.pba.product.catalog.domain.usecase

import com.pba.product.catalog.domain.model.Product
import com.pba.product.catalog.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchProductsUseCase(
    private val repo: ProductRepository
) {

    operator fun invoke(keyword: String): Flow<List<Product>> =
        repo.searchProducts(keyword).map { result ->
            result.getOrDefault(emptyList())
        }
}