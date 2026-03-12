package com.pba.di

import com.pba.product.catalog.di.moduleProductCatalog
import org.koin.dsl.module

val modulesApp = module {
    includes(moduleProductCatalog)
}