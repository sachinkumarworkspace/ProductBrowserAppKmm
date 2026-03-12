package com.pba.di

import com.pba.product.catalog.data.local.ProductDatabase
import org.koin.dsl.module

val modulePlatform = module {
    single { createHttpClient() }
    single { createDatabase() }
    single { get<ProductDatabase>().productDao() }
}