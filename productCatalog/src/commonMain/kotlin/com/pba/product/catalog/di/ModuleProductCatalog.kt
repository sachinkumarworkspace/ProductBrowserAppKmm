package com.pba.product.catalog.di

import com.pba.product.catalog.data.remote.api.ProductApi
import com.pba.product.catalog.data.repository.ImplProductRepository
import com.pba.product.catalog.domain.repository.ProductRepository
import com.pba.product.catalog.domain.usecase.GetProductsUseCase
import com.pba.product.catalog.domain.usecase.GetSingleProductUseCase
import com.pba.product.catalog.domain.usecase.SearchProductsUseCase
import com.pba.product.catalog.ui.viewmodel.ProductDetailsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import com.pba.product.catalog.ui.viewmodel.ProductListViewModel
import io.ktor.client.HttpClient

val moduleProductCatalog = module {
    single { ProductApi(get<HttpClient>()) }

    factory<ProductRepository> { ImplProductRepository(get(), get()) }

    factory { GetProductsUseCase(get()) }
    factory { SearchProductsUseCase(get()) }
    viewModelOf(::ProductListViewModel)

    factory { GetSingleProductUseCase(get()) }
    viewModelOf(::ProductDetailsViewModel)
}