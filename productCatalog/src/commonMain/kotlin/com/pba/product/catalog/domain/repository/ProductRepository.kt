package com.pba.product.catalog.domain.repository

import com.pba.product.catalog.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(): Flow<List<Product>>
    fun viewProductDetails(productId: Int): Flow<Product>
    fun searchProducts(keyword: String): Flow<List<Product>>
    suspend fun filterProducts(category: String): List<Product>
}