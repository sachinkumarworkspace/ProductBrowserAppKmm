package com.pba.product.catalog.data.mapper

import com.pba.product.catalog.data.remote.dto.ProductDtoResponse
import com.pba.product.catalog.domain.model.Product

fun ProductDtoResponse.toDomain(): Product {
    return Product(
        id = id,
        name = title,
        price = price,
        thumbnail = thumbnail ?: "",
        description = description,
        rating = rating,
        brand =brand,
        category = category,
    )
}