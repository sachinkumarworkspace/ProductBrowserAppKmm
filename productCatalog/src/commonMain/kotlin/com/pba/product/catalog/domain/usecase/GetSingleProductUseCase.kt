package com.pba.product.catalog.domain.usecase

import com.pba.product.catalog.domain.model.Product
import com.pba.product.catalog.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map

class GetSingleProductUseCase(
    private val productRepository: ProductRepository
) {

    operator fun invoke(productId: Int?): Flow<Product> {
        return productId?.let { id ->
            productRepository.viewProductDetails(id)
                .map { result ->
                    result.getOrElse { throw it }
                }
        } ?: emptyFlow()
    }
}