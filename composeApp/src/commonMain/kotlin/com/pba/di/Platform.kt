package com.pba.di

import com.pba.product.catalog.data.local.ProductDatabase
import io.ktor.client.HttpClient

expect fun createHttpClient(): HttpClient
expect fun createDatabase(): ProductDatabase