package com.pba.product.catalog.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val thumbnail: String,
    val description: String,
    val rating: Double,
    val category: String,
)
