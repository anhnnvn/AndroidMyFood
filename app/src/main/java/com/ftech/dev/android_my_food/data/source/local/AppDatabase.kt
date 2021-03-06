package com.ftech.dev.android_my_food.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ftech.dev.android_my_food.utils.getApplication

@Database(entities = [
    SearchEntity::class,
    ItemInCartEntity::class,
    OrderEntity::class],
    version = 3)
abstract class AppDatabase : RoomDatabase() {

    abstract fun searchDao(): SearchDao
    abstract fun itemInCartDao(): ItemInCartDao
    abstract fun orderDao(): OrderDao

    companion object {
        fun getInstance(): AppDatabase {
            val db = Room.databaseBuilder(
                getApplication(),
                AppDatabase::class.java,
                "app.db"
            )
                .allowMainThreadQueries()
                .addMigrations()
                .fallbackToDestructiveMigration()
                .build()

            return db
        }
    }
}

