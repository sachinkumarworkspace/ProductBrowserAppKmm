package com.pba.product.catalog.domain.repository

import com.pba.product.catalog.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(): Flow<Result<List<Product>>>
    fun viewProductDetails(productId: Int): Flow<Result<Product>>
    fun searchProducts(keyword: String): Flow<Result<List<Product>>>
}