package com.pba.di

import android.content.Context
import androidx.room.Room
import com.pba.product.catalog.data.local.ProductDatabase
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

lateinit var appContext: Context

actual fun createHttpClient(): HttpClient {
    return HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
    }
}

actual fun createDatabase(): ProductDatabase {
    return Room.databaseBuilder(
        appContext,
        ProductDatabase::class.java,
        "product_db"
    ).build()
}