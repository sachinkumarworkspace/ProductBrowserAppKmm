package com.pba.product.catalog.domain.usecase

import com.pba.product.catalog.domain.model.Product
import com.pba.product.catalog.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProductsUseCase(
    private val repo: ProductRepository
) {

    operator fun invoke(): Flow<List<Product>> = repo.getProducts()
}