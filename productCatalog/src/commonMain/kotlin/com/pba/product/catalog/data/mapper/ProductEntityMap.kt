package com.pba.product.catalog.data.mapper

import com.pba.product.catalog.data.local.entity.ProductEntity
import com.pba.product.catalog.data.remote.dto.ProductDtoResponse
import com.pba.product.catalog.domain.model.Product

fun ProductEntity.toDomain(): Product {
    return Product(
        id = id,
        name = title,
        price = price,
        thumbnail = thumbnail ?: "",
        description = description,
        rating = rating,
        category = category
    )
}

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        title = name,
        price = price,
        thumbnail = thumbnail,
        description = description,
        rating = rating,
        category = category
    )
}

fun ProductDtoResponse.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        title = title,
        description = description,
        category = category,
        price = price,
        rating = rating,
        thumbnail = thumbnail
    )
}