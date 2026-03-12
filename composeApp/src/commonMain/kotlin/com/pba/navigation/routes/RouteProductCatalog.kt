package com.pba.navigation.routes

import kotlinx.serialization.Serializable


sealed interface RouteProductCatalog {
    @Serializable
    object ProductList : RouteProductCatalog

    @Serializable
    data class ProductDetails(val productId: Int) : RouteProductCatalog
}