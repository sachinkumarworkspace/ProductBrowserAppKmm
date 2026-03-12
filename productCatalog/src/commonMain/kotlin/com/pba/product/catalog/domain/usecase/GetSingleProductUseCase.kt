package com.pba.product.catalog.domain.usecase

import com.pba.product.catalog.domain.model.Product
import com.pba.product.catalog.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class GetSingleProductUseCase(
    private val productRepository: ProductRepository
) {
    operator fun invoke(productId: Int?): Flow<Product> {
        return productId?.let {
            productRepository.viewProductDetails(it)
        } ?: emptyFlow()
    }
}