package com.pba.product.catalog.data.repository

import com.pba.product.catalog.data.local.db.ProductDao
import com.pba.product.catalog.data.mapper.toDomain
import com.pba.product.catalog.data.mapper.toEntity
import com.pba.product.catalog.data.remote.api.ProductApi
import com.pba.product.catalog.domain.model.Product
import com.pba.product.catalog.domain.repository.ProductRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class ImplProductRepository(
    private val api: ProductApi,
    private val dao: ProductDao
) : ProductRepository {

    private var isFetchedThisSession = false

    override fun getProducts() = flow {
        try {

            if (!isFetchedThisSession) {
                val response = api.getProducts()
                val entities = response.products.map {
                    it.toEntity()
                }
                dao.clearProducts()
                dao.insertProducts(entities)
                isFetchedThisSession = true
            }

            dao.getProducts()
                .map { entities ->
                    entities.map { it.toDomain() }
                }
                .collect { products ->
                    emit(Result.success(products))
                }

        } catch (e: Exception) {

            emit(Result.failure(e))

        }
    }

    override fun viewProductDetails(productId: Int) = flow {
        try {

            val response = api.getProductDetails(productId)
            val product = response.toDomain()
            emit(Result.success(product))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override fun searchProducts(keyword: String) = flow {
        try {
            val response = api.searchProducts(keyword)
            val products = response.products.map { dto ->
                dto.toDomain()
            }
            emit(Result.success(products))
        } catch (e: Exception) {
            emit(Result.failure(e))

        }
    }
}