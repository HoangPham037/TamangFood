package com.example.tamangfood.ui.search.searchchild.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tamangfood.ui.search.searchchild.local.dao.RecentSearchesDao
import com.example.tamangfood.ui.search.searchchild.local.model.RecentSearchesData

@Database(entities = [RecentSearchesData::class], version = 1, exportSchema = false)
abstract class RecentSearchesDatabase : RoomDatabase() {
    abstract fun recentSearchesDao(): RecentSearchesDao
}
