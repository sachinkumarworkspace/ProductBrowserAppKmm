package com.pba.product.catalog.data.remote.api

import com.pba.product.catalog.data.remote.dto.ProductDtoResponse
import com.pba.product.catalog.data.remote.dto.ProductsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ProductApi(
    private val client: HttpClient
) {

    suspend fun getProducts() =
        client.get("https://dummyjson.com/products").body<ProductsResponse>()

    suspend fun searchProducts(keyword: String): ProductsResponse {
        return client.get("https://dummyjson.com/products/search") {
            url {
                parameters.append("q", keyword)
            }
        }.body()
    }

    suspend fun getProductDetails(id: Int): ProductDtoResponse {
        return client.get("https://dummyjson.com/products/$id").body()
    }

    suspend fun getCategoryProducts(category: String): ProductsResponse {
        return client.get("https://dummyjson.com/products/category/$category")
            .body()
    }
}