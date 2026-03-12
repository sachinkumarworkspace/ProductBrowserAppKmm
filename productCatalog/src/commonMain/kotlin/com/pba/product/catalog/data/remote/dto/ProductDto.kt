package com.pba.product.catalog.data.remote.dto

import kotlinx.serialization.Serializable


@Serializable
data class ProductsResponse(
    val products: List<ProductDtoResponse>
)

@Serializable
data class ProductDtoResponse(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val price: Double,
    val rating: Double,
    val brand: String?="Unknown",
    val thumbnail: String?
)