package com.pba.product.catalog.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pba.product.catalog.data.local.db.ProductDao
import com.pba.product.catalog.data.local.entity.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1
)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}