package com.pba.di

import androidx.room.Room
import com.pba.product.catalog.data.local.ProductDatabase
import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import platform.Foundation.NSHomeDirectory
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

actual fun createHttpClient(): HttpClient {
    return HttpClient(Darwin) {
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
    val dbPath = NSHomeDirectory() + "/product_db.db"

    return Room.databaseBuilder<ProductDatabase>(
        name = dbPath
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}